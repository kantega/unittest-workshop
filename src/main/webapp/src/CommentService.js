
function CommentService() {
}


CommentService.prototype.getComments = function (articleId) {
    return $.ajax({
        url: "articles/" + articleId + "/comments",
        dataType: "json"
    });
};


