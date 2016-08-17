package web.rest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class StaticResource {

    private static final Logger logger = Logger.getLogger(StaticResource.class.getName());



    @GET
    @Path("{path:.*}")
    public byte[] Get(@PathParam("path") String path) {
        try {
        	System.err.println("path "+path);
        	if (path == null || "".equals(path))
                return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/index.html"));
        	else if (path.startsWith("bower_components"))
                return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/" + path));
        	else
        		return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/"+path));
        } catch (IOException e) {
            return null;
        }
    }

}
