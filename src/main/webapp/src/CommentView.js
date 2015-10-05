function CommentView(container) {
    this.container = container;
    this.dateUtil = new DateUtil();
}


CommentView.prototype.showComments = function (comments) {
    var ul = $('<ul/>');
    for (var i = 0; i < comments.length; i++) {
        var comment = comments[i];
        var li = $('<li/>');
        li.appendTo(ul);
        $('<p/>').html(comment.text).appendTo(li);
        $('<small/>').html('Posted ' + this.dateUtil.durationAsText(new Date(comment.timestamp)) + ' by ' + comment.by).appendTo(li);
    }
    this.container.html(ul);
};
