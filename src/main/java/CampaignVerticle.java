import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;


public class CampaignVerticle extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        Route userRoute = router.get("/api/campaigns?:user_id");

        userRoute.handler(rc -> {
            String userId = rc.request().getParam("user_id");
            // clean the param

            // get data
            // [    {
                        //“Id” : 123,
                        //“name” : “my campaign”,
                        //“data”: {
                            //<some data>
                    //}
                    //},{
                        //“Id” : 123,
                        //“name” : “my campaign”,
                        //“data”: {
                            //<some data>
                    //}
            //}]


            if (!userId.isEmpty()) {
                rc.response().end("[\n" +
                        "  { id: 11, name: 'Dr Nice' },\n" +
                        "  { id: 12, name: 'Narco' },\n" +
                        "  { id: 13, name: 'Bombasto' },\n" +
                        "  { id: 14, name: 'Celeritas' },\n" +
                        "  { id: 15, name: 'Magneta' },\n" +
                        "  { id: 16, name: 'RubberMan' },\n" +
                        "  { id: 17, name: 'Dynama' },\n" +
                        "  { id: 18, name: 'Dr IQ' },\n" +
                        "  { id: 19, name: 'Magma' },\n" +
                        "  { id: 20, name: 'Tornado' }\n" +
                        "]");
            } else {
                rc.response().end("");
            }
        });

        router.get().handler(StaticHandler.create());

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CampaignVerticle());
    }
}
