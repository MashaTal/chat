package chat.controller;


import chat.entities.Room;
import chat.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(value = "/rooms")
public class RoomController {
    private RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired(required = true)
    @Qualifier(value = "roomService")
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String addRoom(@RequestParam("userId") int userId, @RequestBody Room room) {
        logger.info("adding user....");
        return this.roomService.addRoom(userId,room);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Room> listRooms(@RequestParam("userId") String userId) {
        return this.roomService.getListRoomByUserId(userId);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Room> listAllRooms() {
        return this.roomService.getAllListRoom();
    }
}
