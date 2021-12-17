package core.response;

public enum HttpStatus {

    CONTINUE(100,"Continue-This interim response indicates that the client should continue the request or ignore the response if the request is already finished."),
    SWITCHING_PROTOCOLS(101,"This code is sent in response to an Upgrade request header from the client and indicates the protocol the server is switching to."),
    PROCESSING(102,"This code indicates that the server has received and is processing the request, but no response is available yet."),
    EARLY_HITS(103,"This status code is primarily intended to be used with the Link header, letting the user agent start preloading resources while the server prepares a response."),

    OK(200,"The request succeeded. The result meaning of \"success\" depends on the HTTP method:"),
    CREATED(201,"The request succeeded, and a new resource was created as a result. This is typically the response sent after POST requests, or some PUT requests."),
    ACCEPTED(202,"The request has been received but not yet acted upon. It is noncommittal, since there is no way in HTTP to later send an asynchronous response indicating the outcome of the request. It is intended for cases where another process or server handles the request, or for batch processing."),
    NON_AUTHORITATIVE_INFO(203,"This response code means the returned metadata is not exactly the same as is available from the origin server, but is collected from a local or a third-party copy. This is mostly used for mirrors or backups of another resource. Except for that specific case, the 200 OK response is preferred to this status."),
    NO_CONTENT(204,"There is no content to send for this request, but the headers may be useful. The user agent may update its cached headers for this resource with the new ones."),
    RESET_CONTENT(205,"Tells the user agent to reset the document which sent this request."),
    PARTIAL_CONTENT(206,"This response code is used when the Range header is sent from the client to request only part of a resource."),
    MULTI_STATUS(207,"Conveys information about multiple resources, for situations where multiple status codes might be appropriate."),
    ALREADY_REPORTED(208,"Used inside a <dav:propstat> response element to avoid repeatedly enumerating the internal members of multiple bindings to the same collection."),
    IM_USED(226,"The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."),


    MULTIPLE_CHOICE(300,"The request has more than one possible response. The user agent or user should choose one of them. (There is no standardized way of choosing one of the responses, but HTML links to the possibilities are recommended so the user can pick.)"),
    MOVED_PERMANENTLY(301,"The URL of the requested resource has been changed permanently. The new URL is given in the response."),
    FOUND(302,"This response code means that the URI of requested resource has been changed temporarily. Further changes in the URI might be made in the future. Therefore, this same URI should be used by the client in future requests."),
    SEE_OTHER(303,"The server sent this response to direct the client to get the requested resource at another URI with a GET request."),
    NOT_MODIFIED(304,"This is used for caching purposes. It tells the client that the response has not been modified, so the client can continue to use the same cached version of the response."),
    USE_PROXY(305,"Defined in a previous version of the HTTP specification to indicate that a requested response must be accessed by a proxy. It has been deprecated due to security concerns regarding in-band configuration of a proxy."),
    UNUSED(306,"This response code is no longer used; it is just reserved. It was used in a previous version of the HTTP/1.1 specification."),
    TEMPORARY_REDIRECT(307,"The server sends this response to direct the client to get the requested resource at another URI with same method that was used in the prior request. This has the same semantics as the 302 Found HTTP response code, with the exception that the user agent must not change the HTTP method used: if a POST was used in the first request, a POST must be used in the second request."),
    PERMANENT_REDIRECT(308,"This means that the resource is now permanently located at another URI, specified by the Location: HTTP Response header. This has the same semantics as the 301 Moved Permanently HTTP response code, with the exception that the user agent must not change the HTTP method used: if a POST was used in the first request, a POST must be used in the second request."),


    BAD_REQUEST(400,"The server could not understand the request due to invalid syntax."),
    UNAUTHORIZED(401,"Although the HTTP standard specifies \"unauthorized\", semantically this response means \"unauthenticated\". That is, the client must authenticate itself to get the requested response."),
    PAYMENT_REQUIRED(402,"This response code is reserved for future use. The initial aim for creating this code was using it for digital payment systems, however this status code is used very rarely and no standard convention exists."),
    FORBIDDEN(403,"The client does not have access rights to the content; that is, it is unauthorized, so the server is refusing to give the requested resource. Unlike 401 Unauthorized, the client's identity is known to the server."),
    NOT_FOUND(404,"The server can not find the requested resource. In the browser, this means the URL is not recognized. In an API, this can also mean that the endpoint is valid but the resource itself does not exist. Servers may also send this response instead of 403 Forbidden to hide the existence of a resource from an unauthorized client. This response code is probably the most well known due to its frequent occurrence on the web."),
    METHOD_NOT_ALLOWED(405,"The request method is known by the server but is not supported by the target resource. For example, an API may not allow calling DELETE to remove a resource."),


    INTERNAL_SERVER_ERROR(500,"The server has encountered a situation it does not know how to handle."),



    ;
    private final int status;
    private final String message;
    HttpStatus(int status, String description) {
        this.status=status;
        this.message=description;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
