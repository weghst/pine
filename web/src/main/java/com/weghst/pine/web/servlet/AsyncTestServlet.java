/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.pine.web.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = {"/asyncTestServlet"})
public class AsyncTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();

        System.out.println(asyncContext.getTimeout());
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("Async Begin-----------");

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    asyncContext.getResponse().getWriter().write("Hello Async");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                asyncContext.complete();
                System.out.println("Async End-------------");
            }
        });

    }
}
