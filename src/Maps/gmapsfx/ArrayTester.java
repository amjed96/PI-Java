/*
 * Copyright 2014 Geoff Capper.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Maps.gmapsfx;

import Maps.gmapsfx.javascript.JavaFxWebEngine;
import Maps.gmapsfx.javascript.JavascriptArray;
import Maps.gmapsfx.javascript.JavascriptObject;
import Maps.gmapsfx.javascript.JavascriptRuntime;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Geoff Capper
 */
public class ArrayTester extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(ArrayTester.class);
    
    protected WebView webview;
    protected JavaFxWebEngine webengine;
    
    @Override
    public void start(final Stage stage) throws Exception {
        
        webview = new WebView();
        webengine = new JavaFxWebEngine(webview.getEngine());
        JavascriptRuntime.setDefaultWebEngine( webengine );
        
        BorderPane bp = new BorderPane();
        bp.setCenter(webview);
        
        webengine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            runTests();
                            //Platform.exit();
                        }
                    }
                });
        webengine.load(getClass().getResource("/com/lynden/gmapsfx/html/arrays.html").toExternalForm());
        
        Scene scene = new Scene(bp, 600, 600);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    private void runTests() {
        
        JSObject jsWin = (JSObject) webengine.executeScript("window");
        jsWin.call("displayTest", new Object[]{null});
        
        JavascriptArray ary = new JavascriptArray();
        
        int len = 0;
        for (int i = 0; i < 6; i++) {
            len = ary.push("String " + i);
            LOG.debug("testArrays push " + i + " gives len: " + len);
        }
        
        LOG.debug("testArrays toString: " + ary.toString());
        
        ary.reverse();
        
        LOG.debug("testArrays reverse toString: " + ary.toString());
        
        ary.reverse();
        
        Object obj = ary.pop();
        
        LOG.debug("testArrays popped: " + obj);
        LOG.debug("testArrays popped toString: " + ary.toString());
        
        TestJSO jso = new TestJSO();
        jso.setTestName("Test 1");
        
        ary.unshift(jso);
        
        LOG.debug("testArrays unshift JsO toString: " + ary.toString());
        
        Object jso1 = ary.shift();
        
        LOG.debug("testArrays shift JsO: " + jso1);
        LOG.debug("testArrays shift JsO reference equality: " + (jso == jso1));
        LOG.debug("testArrays shift JsO toString: " + ary.toString());
        
        ary.push(jso);
        LOG.debug("testArrays push JsO toString: " + ary.toString());
        
        jsWin.call("displayArray", ary);
        
        jso.setTestName("Altered Test 1");
        
        jsWin.call("displayArray", ary);
        
        LOG.debug("testArrays alter JsO toString: " + ary.toString());
        
        Object jso2 = ary.get(ary.length() - 1);
        LOG.debug("testArrays get JsO2: " + jso2);
        
        jsWin.call("iterateArray", ary);
        
        jsWin.call("displayTestEnd", new Object[]{null});
        
    }
    
    
    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        launch(args);
    }
    
    
    class TestJSO extends JavascriptObject {
        
        public TestJSO() {
            super("Object");
        }
        
        public void setTestName(String testName) {
            setProperty("testName", testName);
        }
        
        public String getTestName() {
            return getProperty("testName", String.class);
        }
        
        @Override
        public String toString() {
            return getTestName();
        }
        
    }
    
}
