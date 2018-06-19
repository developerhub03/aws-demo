package com.learning.aws.SNS;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class AmazonSNSHandler extends AbstractHandler {
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        BlockingQueue<Map<String, String>> messageQueue = new LinkedBlockingQueue<Map<String, String>>();
        Scanner scanner = new Scanner(request.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }

        // Build a message map from the JSON encoded message
        InputStream bytes = new ByteArrayInputStream(sb.toString().getBytes());
        Map<String, String> messageMap = new ObjectMapper().readValue(bytes, Map.class);

        // Enqueue message map for receive loop
        messageQueue.add(messageMap);

        // Set HTTP response
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        ((Request) request).setHandled(true);
    }
}
