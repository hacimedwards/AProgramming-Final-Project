package model;

import java.io.Serializable;
import java.util.UUID;

/**
 * All client → server messages are wrapped in this envelope.
 *
 * The correlationId ties each Request to its Response so the client
 * can match replies even in a multi-threaded environment.
 *
 * Supported actions (server switches on these strings):
 *   Auth:         "LOGIN"
 *   Reservations: "CREATE_RESERVATION", "VIEW_MY_RESERVATIONS",
 *                 "VIEW_ALL_RESERVATIONS", "UPDATE_RESERVATION_STATUS",
 *                 "CANCEL_RESERVATION"
 *   Users:        "ADD_USER", "DELETE_USER"
 */

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String correlationId;   // UUID — ties request to response
    private final String action;          // what the server should do
    private final Object payload;         // the data (cast server-side based on action)
    private final String requestingRole;  // role of the logged-in user (for server-side ACL)
    private final int    requestingUserId;

    public Request(String action, Object payload, String requestingRole, int requestingUserId) {
        this.correlationId    = UUID.randomUUID().toString();
        this.action           = action;
        this.payload          = payload;
        this.requestingRole   = requestingRole;
        this.requestingUserId = requestingUserId;
    }

    /** Use this constructor before login (no role/userId yet). */
    public Request(String action, Object payload) {
        this(action, payload, null, -1);
    }

    public String getCorrelationId()    { return correlationId; }
    public String getAction()           { return action; }
    public Object getPayload()          { return payload; }
    public String getRequestingRole()   { return requestingRole; }
    public int    getRequestingUserId() { return requestingUserId; }

    @Override
    public String toString() {
        return String.format("Request[id=%s, action=%s, role=%s]",
                correlationId, action, requestingRole);
    }
}