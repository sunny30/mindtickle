package com.example.helloworld.resources;

import com.example.helloworld.action.ContentAction;
import com.example.helloworld.action.GroupAction;
import com.example.helloworld.action.SeriesAction;
import com.example.helloworld.action.UserAction;
import com.example.helloworld.core.Saying;
import com.example.helloworld.model.ContentSeriesRelationShip;
import com.example.helloworld.model.EntityMetadata;
import com.example.helloworld.model.SeriesToGroupRelationShip;
import com.example.helloworld.model.UserToGroupRelationShip;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final UserAction addUserAction;
    private final GroupAction addGroupAction;
    private final ContentAction contentAction;
    private final SeriesAction seriesAction;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        addUserAction = new UserAction();
        addGroupAction = new GroupAction();
        contentAction = new ContentAction();
        seriesAction = new SeriesAction();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(),
                String.format(template, name.or(defaultName)));
    }

    @POST
    @Timed
    @Path("user/{userID}")
    public Response addUser(@PathParam("userID") String userId) {
        addUserAction.withUserId(userId).persist();
        return Response.created(URI.create(userId)).build();
    }

    @POST
    @Timed
    @Path("group/{groupId}")
    public Response addGroup(@PathParam("groupId") String groupId) {
        addGroupAction.withGroupId(groupId).persist();
        return Response.created(URI.create(groupId)).build();
    }

    @DELETE
    @Timed
    @Path("user/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) {
        addUserAction.withUserId(userId).removeUser();
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("group/{groupId}")
    public Response deleteGroup(@PathParam("groupId") String groupId) {
        addGroupAction.withGroupId(groupId).removeGroup();
        return Response.ok().build();
    }

    @POST
    @Timed
    @Path("/grant/group/{groupId}/user/{userId}")
    public Response addGroupUserRelationShip(@PathParam("groupId") String groupId,
                                             @PathParam("userId") String userId) {
        EntityMetadata metadata = EntityMetadata.getSingleInstance();
        if (!metadata.isGroupPresent(groupId) || !metadata.isUserPresent(userId))
            throw new RuntimeException("Either group and User must be present");
        UserToGroupRelationShip userToGroupRelationShip = UserToGroupRelationShip.getSingleInstance();
        userToGroupRelationShip.add(userId, groupId);
        return Response.created(URI.create(userId + "added in group" + groupId)).build();
    }

    @DELETE
    @Timed
    @Path("/remove/user/{userId}/group/{groupId}")
    public Response removeUserFromGroup(@PathParam("userId") String userId, @PathParam("groupId") String groupId) {
        EntityMetadata metadata = EntityMetadata.getSingleInstance();
        if (!metadata.isGroupPresent(groupId) || !metadata.isUserPresent(userId))
            throw new RuntimeException("Either group and User must be present");
        UserToGroupRelationShip userToGroupRelationShip = UserToGroupRelationShip.getSingleInstance();
        userToGroupRelationShip.remove(userId, groupId);
        return Response.ok(URI.create(userId + " remove in group " + groupId)).build();
    }

    @POST
    @Timed
    @Path("content/{contentId}")
    public Response addContent(@PathParam("contentId") String contentId) {

        contentAction.withContent(contentId).persist();
        return Response.created(URI.create(contentId)).build();

    }

    @POST
    @Timed
    @Path("series/{seriesId}")
    public Response addSeries(@PathParam("seriesId") String seriesId) {

        seriesAction.withSeriesId(seriesId).persist();
        return Response.created(URI.create(seriesId)).build();

    }

    @POST
    @Timed
    @Path("grant/series/{seriesId}/content/{contentId}")
    public Response addRelationShip(@PathParam("seriesId") String seriesId,@PathParam("contentId") String contentId){
        ContentSeriesRelationShip relationShip = ContentSeriesRelationShip.getSingleInstance() ;
        relationShip.addRelationShip(contentId,seriesId);
        return Response.created(URI.create(seriesId+" grant video "+contentId)).build() ;
    }

    @POST
    @Timed
    @Path("assign/series/{seriesId}/group/{groupId}")
    public Response assignSeriesToGroup(@PathParam("seriesId") String seriesId,@PathParam("groupId") String groupId){
        SeriesToGroupRelationShip seriesToGroupRelationShip = SeriesToGroupRelationShip.getSingleInstance() ;
        seriesToGroupRelationShip.addRelationship(seriesId,groupId);
        return Response.created(URI.create(seriesId+" assigned to group "+groupId)).build() ;
    }

}
