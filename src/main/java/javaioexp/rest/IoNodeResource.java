package javaioexp.rest;

import java.io.StringWriter;
import java.nio.file.Files;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javaioexp.util.PathsBuilder;

@Path("/ionodes")
public class IoNodeResource {
	public static final String SUB_PATH = "projects";
	public static String BASE_PATH = "/Users/Adam/temp2/experience";
	@POST
	@Consumes("text/plain")
	@Produces("application/json")
    public Response getClichedMessage(String path) {
		//PathsBuilder pathsBuilder = new PathsBuilder("/experience/projects");
        try {
        	
        	PathsBuilder pathsBuilder = new PathsBuilder(BASE_PATH, SUB_PATH);
        	return Response.ok(pathsBuilder.toJsonString()).build();
			//return pathsBuilder.toJsonString();
		} catch (Exception e) {
			e.printStackTrace();
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			jsonArrayBuilder.add(e.getMessage());
			StringWriter stringWriter = new StringWriter();
			JsonWriter jsonWriter = Json.createWriter(stringWriter);
			jsonWriter.writeArray(jsonArrayBuilder.build());
			jsonWriter.close();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(stringWriter.getBuffer().toString()).build();

		}
    }
	
	@GET
	public String getStatus() {
		
		return "ok";
	}
}
