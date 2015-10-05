describe("commentView", function() {

    var container;
    var comments;

    beforeEach(function () {
        container = $('<div/>');
        var day = 1000 * 60 * 60 * 24;
        var date = new Date(new Date() - day);
        comments = [
            {
                timestamp: date.toString(),
                text: "some text",
                by: "someone"
            },
            {
                timestamp: date.toString(),
                text: "some other text",
                by: "someone else"
            }
        ];
    });

    it("shall view comments in ul element", function() {

        var commentView = new CommentView(container);

        commentView.showComments(comments);

        expect(container.find('ul').size()).toBe(1);
    });

    it("shall create one li element for each comment", function() {

        var commentView = new CommentView(container);

        commentView.showComments(comments);

        expect(container.find('li').size()).toBe(comments.length);
    });

    it("shall view comment text", function() {

        //given
        var commentView = new CommentView(container);
        var comment = {
            timestamp: new Date().toString(),
            text: "some text",
            by: ""
        };

        //when
        commentView.showComments([ comment ]);

        expect(container.find('p').text()).toMatch(comment.text);
    });

    it("shall view commenter and duration", function() {

        //given
        var commentView = new CommentView(container);
        var oneDay = 1000 * 60 * 60 * 24;
        var yesterday = new Date(new Date() - oneDay);
        var comment = {
            timestamp: yesterday.toString(),
            text: "",
            by: "someone"
        };

        //when
        commentView.showComments([ comment ]);

        expect(container.find('small').text()).toMatch(comment.by);
        expect(container.find('small').text()).toMatch("1 day ago");

    });

    it("shall not view old comments", function() {

        //given
        var commentView = new CommentView(container);
        var oldComment = {
            timestamp: new Date().toString(),
            text: "some old text",
            by: "some old geezer"
        };
        commentView.showComments([ oldComment ]);

        //when
        commentView.showComments(comments);

        //then
        expect(container.find('li').size()).toBe(comments.length);
        expect(container.find('p').text()).not.toMatch(oldComment.text);
    });

});


