package com.github.polimi_mt_acg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.FileNotFoundException;

/**
 * REST Resource of the PhotoBook
 */
@Path("/photobook")
public class PhotoBookResource {

    /**
     * Replies to a GET request with string representation of the Photos available in the PhotoBook.
     *
     * @param uriInfo Inject reference to UriInfo of the request.
     * @return An OK response with string representation of the Photos available in the PhotoBook.
     */
    @GET
    @Produces("text/plain")
    public Response getPhotoBookAsPlain(@Context UriInfo uriInfo) {
        System.out.println("text/plain GET request at URI: " + uriInfo.getAbsolutePath());
        PhotoBook pb = PhotoBook.getInstance();
        StringBuilder sb = new StringBuilder();

        for (Photo img : pb.getPics()) {
            try {
                String repr = img.buildRepresentation();
                sb.append(repr).append('\n');
            } catch (FileNotFoundException e) {
                System.out.println("Error in building string representation" +
                        "of" + img.getFileName());
            }
        }

        return Response.ok(sb.toString()).build();
    }

    @GET
    @Produces("application/json")
    public PhotoBook getPhotoBookAsJSON(@Context UriInfo uriInfo) {
        System.out.println("application/json GET request at URI: " + uriInfo.getAbsolutePath());
        return PhotoBook.getInstance();
    }
}
