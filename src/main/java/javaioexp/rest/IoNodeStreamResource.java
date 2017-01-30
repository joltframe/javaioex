package javaioexp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.IOUtils;

@Path("ionodestr")
public class IoNodeStreamResource {
	
	@POST
	@Consumes("text/plain")
//    @Path("/fetch")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response fetchFile(String path) 
//    @QueryParam("path") String path
        throws Exception {
		//	String path = "/experience/projects/project1/src/Test1.java";
        final File file = new File(path);
        System.out.println(path);

        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException {
                try {
                	 byte[] fileContent = Files.readAllBytes(Paths.get(path));
                	 System.out.println("length of file : " + fileContent.length);
                    output.write(fileContent);
                   // output.write(IOUtils.toByteArray(new FileInputStream(file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        return Response.ok(stream, MediaType.APPLICATION_OCTET_STREAM)
            //.header("Content-Disposition", "inline; filename=\"" + file.getName() + "\"") 
            .build();
    }
//	 @GET
//	    @Produces(MediaType.TEXT_PLAIN)
//	    @Path("/{nodeId}/{depth}")
//	    public Response hello(@PathParam("nodeId") long nodeId, @PathParam("depth") int depth) {
//	        
//	 
//	        StreamingOutput stream = new StreamingOutput() {
//	            @Override
//	            public void write(OutputStream os) throws IOException, WebApplicationException {
//	                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
//	 
//	                for (org.neo4j.graphdb.Path path : paths) {
//	                    writer.write(path.toString() + "\n");
//	                }
//	                writer.flush();
//	            }
//	        };
//	 
//	        return Response.ok(stream).build();
//	    }
}
