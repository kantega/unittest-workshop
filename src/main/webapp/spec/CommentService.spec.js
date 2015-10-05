describe("commentService", function() {

    var xhr;
    var request;
    var commentService;

    beforeEach(function () {

        commentService = new CommentService();

        xhr = sinon.useFakeXMLHttpRequest();
    });

    afterEach(function () {
        xhr.restore();
    });

    it("shall make a GET request to a server to get comments (xhr)", function() {

        //given
        xhr.onCreate = function (xhr) {
            request = xhr;
        };

        //when
        commentService.getComments(42);

        //then
        expect(request.method).toEqual("GET");
        expect(request.url).toEqual("articles/42/comments");

    });


});


describe("commentService", function() {

    var server;
    var commentService;
    var comments;
    var callback;

    beforeEach(function () {

        commentService = new CommentService();
        comments = [{ timestamp: "2015-01-01T20:24:17Z", text: "some text", by: "someone" }];

        server = sinon.fakeServer.create();
        server.respondImmediately = true;

        callback = sinon.spy();
    });

    afterEach(function () {
        server.restore();
    });

    it("shall make a GET request to a server to get comments", function() {

        //given
        server.respondWith(
            "GET", "articles/42/comments", "{}"
        );

        //when
        commentService.getComments(42).then(callback);

        //then
        expect(callback.calledOnce).toBeTruthy();

    });

    it("shall return a promise", function () {

        //when
        var promise = commentService.getComments(42);

        //then
        expect(typeof promise.then).toEqual("function");
        expect(typeof promise.fail).toEqual("function");

    });

    it("shall invoke callback function with deserialized comments", function () {

        //given
        server.respondWith(
            [
                200,
                { "Content-Type": "application/json" },
                JSON.stringify(comments)
            ]
        );

        //when
        commentService.getComments(42).then(callback);

        //then
        expect(callback.firstCall.args[0]).toEqual(comments);
        //expect(callback.calledWith(comments)).toBeTruthy();

    });

    it("shall invoke failure callback with error status when request fails", function () {

        //given
        var errorStatus = 500;
        server.respondWith(
            [
                errorStatus,
                { "Content-Type": "application/json" },
                "{}"
            ]
        );

        //when
        commentService.getComments(42).fail(callback);

        //then
        //expect(callback.getCall(0).args[0].status).toEqual(errorStatus);
        expect(callback.firstCall.args[0].status).toEqual(errorStatus);

    });


});
















