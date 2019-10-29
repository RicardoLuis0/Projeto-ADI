package com.ricardoluis;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/game/";
    private static final boolean AUTOSTART=true;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer newServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.ricardoluis package
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new ResourceConfig() {
        	{
        		packages("com.ricardoluis");
        		register(new CORSFilter());
        	}
        }, false, null, false);
    }
    private static HttpServer server=null;
    private static JFrame window=null;
    private static JButton startstop=null;
    private static void stopServer() {
    	if(server!=null) {
	    	System.out.println("Stopping Server...");
	    	server.shutdownNow();
	    	server=null;
	        startstop.setText("Start Server");
    	}
    }
    private static void startServer() {
    	stopServer();
    	try {
	        // Start the server.
    		server = newServer();
            server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("http"),"/");
	        System.out.println("Starting Server...");
	        server.start();
	        try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
	        System.out.println(String.format("WADL available at %sapplication.wadl\n", BASE_URI));
	        startstop.setText("Stop Server");
	    } catch (final IOException e) {
	    	System.out.println("Failed to start Server,");
	        e.printStackTrace();
	        if(server!=null) {
	        	server.shutdownNow();
	        	server=null;
	        }
	    }
    }
    private static void startStopServer() {
    	if(server!=null) {
    		stopServer();
    	}else {
    		startServer();
    	}
    }
    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        startstop=new JButton("Start Server");
        startstop.addActionListener(a->startStopServer());
        window=new JFrame("Server Control");
        window.setSize(640, 480);
        window.setLocationRelativeTo(null);
        window.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	stopServer();
            	e.getWindow().dispose();
            }
        });
        window.setLayout(new GridBagLayout());
        window.add(startstop,new GridBagConstraints());
        if(AUTOSTART)startServer();
        window.setVisible(true);
    }
}

