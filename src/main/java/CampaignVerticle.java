import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import model.Campaign;
import model.User;

import java.util.*;
import java.util.stream.Collectors;


public class CampaignVerticle extends AbstractVerticle{

    private Map<Integer, Campaign> campaigns = new LinkedHashMap<>();
    private Map<Integer, User> users = new LinkedHashMap<>();

    @Override
    public void start() throws Exception {

        createSomeData();

        Router router = Router.router(vertx);

        // Main API
        router.get("/api/campaigns").handler(this::getByUser);

        // CRUD APIs for campaigns
        router.get("/api/campaigns/all").handler(this::getAll);

        router.post("/api/campaigns").handler(this::addOne);
        router.get("/api/campaigns/:id").handler(this::getOne);
        router.put("/api/campaigns/:id").handler(this::updateOne);
        router.delete("/api/campaigns/:id").handler(this::deleteOne);

        router.get().handler(StaticHandler.create());

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    private void addOne(RoutingContext routingContext) {

        final Campaign campaign = Json.decodeValue(routingContext.getBodyAsString(),
                Campaign.class);

        campaigns.put(campaign.getId(), campaign);

        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
//                .putHeader("Access-Control-Allow-Origin", "*")
//                .putHeader("Access-Control-Allow-Methods", "GET,POST")
//                .putHeader("Referrer-Policy", "no-referrer-when-downgrade")
                .end(Json.encodePrettily(campaign));
    }

    private void getOne(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        if (id == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            final Integer idAsInteger = Integer.valueOf(id);
            Campaign campaign = campaigns.get(idAsInteger);
            if (campaign == null) {
                routingContext.response().setStatusCode(404).end();
            } else {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(campaign));
            }
        }
    }

    private void updateOne(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            final Integer idAsInteger = Integer.valueOf(id);
            Campaign campaign = campaigns.get(idAsInteger);
            if (campaign == null) {
                routingContext.response().setStatusCode(404).end();
            } else {
                campaign.setName(json.getString("name"));
                Campaign.Cap cap = campaign.getCap();

                JsonObject cap_json = json.getJsonObject("cap");
                cap.setMaxCountPerUser(Integer.parseInt(cap_json.getString("maxCountPerUser")));
                cap.setMaxCount(Integer.parseInt(cap_json.getString("maxCount")));

                campaign.setCap(cap);
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(campaign));
            }
        }
    }

    private void deleteOne(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        if (id == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            Integer idAsInteger = Integer.valueOf(id);
            campaigns.remove(idAsInteger);
        }
        routingContext.response().setStatusCode(204).end();
    }

    private void getAll(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .putHeader("Access-Control-Allow-Origin", "*")
                .putHeader("Access-Control-Allow-Methods", "GET")
                .end(Json.encodePrettily(campaigns.values()));
    }

    private void getByUser(RoutingContext rc) {
        String userIdFromRequest = rc.request().getParam("user_id");

        if (userIdFromRequest.isEmpty()) {
            rc.response().end("");
            return;
        }

        int userId = Integer.parseInt(userIdFromRequest);
        List<Campaign> result = new LinkedList<>();

        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }

        User user = users.get(userId);
        List<Campaign> relevantCampaigns = campaigns.values().stream().filter(
                campaign -> user.getUserCampaigns().getOrDefault(campaign.getId(), 0) < campaign.getCap().getMaxCountPerUser() &&
            campaign.getImpressions() < campaign.getCap().getMaxCount()
        ).collect(Collectors.toList());

        // Increment counters
        relevantCampaigns.forEach(campaign ->
        {
            campaign.incrementImpressions();
            user.getUserCampaigns().merge(campaign.getId(), 1, Integer::sum);
        });

        // return result
        rc.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(relevantCampaigns));
    }

    private void createSomeData() {
        Campaign c1 = new Campaign("campaign 1", "", new Campaign.Cap(40, 100));
        campaigns.put(c1.getId(), c1);

        Campaign c2 = new Campaign("campaign 2", "", new Campaign.Cap(60, 120));
        campaigns.put(c2.getId(), c2);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CampaignVerticle());
    }
}
