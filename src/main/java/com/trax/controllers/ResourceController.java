package com.trax.controllers;

import com.google.gson.Gson;
import com.trax.services.contact.ContactService;
import com.trax.services.owner.OwnerService;
import com.trax.services.role.RoleService;
import com.trax.services.session.SessionService;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Location for the frontend to access crud operations
 */

@Controller
@RequestMapping(value="/resources")
public class ResourceController {
    Gson gson = new Gson();

    private String renderSuccess(Object object){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        response.put("object", object);
        return gson.toJson(response);
    }

    private String renderError(String message){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", false);
        response.put("message", message);
        return gson.toJson(response);
    }

    //region Contact
    @Autowired
    ContactService contactService;
    @ResponseBody
    @RequestMapping(value="/contact/list", method= RequestMethod.GET)
    public String listContacts(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/contact/add", method= RequestMethod.POST)
    public String addContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/contact/update", method= RequestMethod.POST)
    public String updateContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/contact/delete", method= RequestMethod.POST)
    public String deleteContact(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Location
//    @Autowired
//    LocationService locationService;
    @ResponseBody
    @RequestMapping(value="/location/list", method= RequestMethod.GET)
    public String listLocations(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/location/add", method= RequestMethod.POST)
    public String addLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/location/update", method= RequestMethod.POST)
    public String updateLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/location/delete", method= RequestMethod.POST)
    public String deleteLocation(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Owner
    @Autowired
    OwnerService ownerService;
    @ResponseBody
    @RequestMapping(value="/owner/list", method= RequestMethod.GET)
    public String listOwners(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/owner/add", method= RequestMethod.POST)
    public String addOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/update", method= RequestMethod.POST)
    public String updateOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/owner/delete", method= RequestMethod.POST)
    public String deleteOwner(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Role
    @Autowired
    RoleService roleService;
    @ResponseBody
    @RequestMapping(value="/role/list", method= RequestMethod.GET)
    public String listRoles(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/role/add", method= RequestMethod.POST)
    public String addRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/role/update", method= RequestMethod.POST)
    public String updateRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/role/delete", method= RequestMethod.POST)
    public String deleteRole(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Session
    @Autowired
    SessionService sessionService;
    @ResponseBody
    @RequestMapping(value="/session/list", method= RequestMethod.GET)
    public String listSessions(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/session/add", method= RequestMethod.POST)
    public String addSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/session/update", method= RequestMethod.POST)
    public String updateSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/session/delete", method= RequestMethod.POST)
    public String deleteSession(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region User
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping(value="/user/list", method= RequestMethod.GET)
    public String listUsers(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/user/add", method= RequestMethod.POST)
    public String addUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/update", method= RequestMethod.POST)
    public String updateUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/user/delete", method= RequestMethod.POST)
    public String deleteUser(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Venue
    @Autowired
    VenueService venueService;
    @ResponseBody
    @RequestMapping(value="/venue/list", method= RequestMethod.GET)
    public String listVenues(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/venue/add", method= RequestMethod.POST)
    public String addVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/update", method= RequestMethod.POST)
    public String updateVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/venue/delete", method= RequestMethod.POST)
    public String deleteVenue(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Activity
//    @Autowired
//    ActivityService activityService;
    @ResponseBody
    @RequestMapping(value="/activity/list", method= RequestMethod.GET)
    public String listActivities(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/activity/add", method= RequestMethod.POST)
    public String addActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/activity/update", method= RequestMethod.POST)
    public String updateActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/activity/delete", method= RequestMethod.POST)
    public String deleteActivity(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

    //region Participant
//    @Autowired
//    ParticipantService participantService;
    @ResponseBody
    @RequestMapping(value="/participant/list", method= RequestMethod.GET)
    public String listParticipants(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value="/participant/add", method= RequestMethod.POST)
    public String addParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/participant/update", method= RequestMethod.POST)
    public String updateParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}

    @ResponseBody
    @RequestMapping(value="/participant/delete", method= RequestMethod.POST)
    public String deleteParticipant(@RequestBody String requestJson, Principal principal){
        String response;
        try{
            response = renderSuccess(new Object());
        } catch (Exception ex){
            response = renderError(ex.getMessage());
        }
        return response;}
    //endregion

//region default Crud Methods
//    @ResponseBody
//    @RequestMapping(value="/list", method= RequestMethod.GET)
//    public String list(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/add", method= RequestMethod.POST)
//    public String add(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/update", method= RequestMethod.POST)
//    public String update(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//
//    @ResponseBody
//    @RequestMapping(value="/delete", method= RequestMethod.POST)
//    public String delete(@RequestBody String requestJson, Principal principal){
//        String response;
//        try{
//            response = renderSuccess(new Object());
//        } catch (Exception ex){
//            response = renderError(ex.getMessage());
//        }
//        return response;}
//region
}
