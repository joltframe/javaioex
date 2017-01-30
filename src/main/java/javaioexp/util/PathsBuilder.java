package javaioexp.util;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import javaioexp.model.IoNodeType;

public class PathsBuilder {
	Path path;

	public PathsBuilder(String rootPath, String path) {
		super();
		this.path = Paths.get(rootPath, path);
	}
	
	public String toJsonString()  throws Exception {
		//Path path = Paths.get("/experience/projects");
		//List<IoNode> ioNodes = new ArrayList<>();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		Map<String, ?> config = null;
		JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(config);
		
		Files.walkFileTree(path, new FileVisitor<Path>() {
			  @Override
			  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			    //System.out.println("pre visit dir:" + dir);
				  //ioNodes.add(new IoNode(IoNodeType.DIRECTORY, dir.toString()));
				  JsonObjectBuilder jsonObjectBuilder = jsonBuilderFactory.createObjectBuilder()
						  .add("fqName", dir.toString()).add("ioNodeType", IoNodeType.DIRECTORY.toString());
				  jsonArrayBuilder.add(jsonObjectBuilder);
			    return FileVisitResult.CONTINUE;
			  }

			  @Override
			  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			    //System.out.println("visit file: " + file);
				//  ioNodes.add(new IoNode(IoNodeType.FILE, file.toString()));
				  JsonObjectBuilder jsonObjectBuilder = jsonBuilderFactory.createObjectBuilder()
						  .add("fqName", file.toString()).add("ioNodeType", IoNodeType.FILE.toString());
				  jsonArrayBuilder.add(jsonObjectBuilder);
			    return FileVisitResult.CONTINUE;
			  }

			  @Override
			  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			    System.out.println("visit file failed: " + file);
			    return FileVisitResult.CONTINUE;
			  }

			  @Override
			  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			    System.out.println("post visit directory: " + dir);
			    return FileVisitResult.CONTINUE;
			  }
			});
		StringWriter stringWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stringWriter);
		jsonWriter.writeArray(jsonArrayBuilder.build());
		jsonWriter.close();
		return stringWriter.getBuffer().toString();
		//	System.out.println(ioNodes);
	}
}
