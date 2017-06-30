package chatbot.app;

import chatbot.Application;
import chatbot.couchbase.Chat;
import chatbot.couchbase.ChatRepository;
import chatbot.lib.Platform;
import chatbot.lib.request.Request;
import chatbot.lib.request.RequestRouter;
import chatbot.lib.response.Response;
import chatbot.rivescript.RiveScriptBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by ramgathreya on 5/19/17.
 */
@RestController
@RequestMapping("/webhook")
public class AppHandler {
    private static final Logger logger = LoggerFactory.getLogger(AppHandler.class);
    private final Application.Helper helper;

    @Autowired
    AppHandler(final Application.Helper helper) {
        this.helper = helper;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Response> handleRequest(@RequestBody final Request request) throws Exception {
        request.setPlatform(Platform.WEB);
        return (List<Response>) new RequestRouter(request, helper).routeRequest();
    }
}
