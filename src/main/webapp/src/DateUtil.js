/**
 * borrowed from Christian Johansen: https://msdn.microsoft.com/en-us/magazine/gg655487.aspx
 */

function DateUtil() {
}

(function () {
    var units = {
        second: 1000,
        minute: 1000 * 60,
        hour: 1000 * 60 * 60,
        day: 1000 * 60 * 60 * 24,
        week: 1000 * 60 * 60 * 24 * 7,
        month: 1000 * 60 * 60 * 24 * 30
    };

    function format(num, type) {
        return num + " " + type + (num > 1 ? "s" : "");
    }

    DateUtil.prototype.durationAsText = function (date, compareTo) {
        compareTo = compareTo || new Date();
        var diff = compareTo - date;
        var future = diff < 0;
        diff = Math.abs(diff);
        var humanized;

        if (diff > units.month) {
            humanized = "more than a month";
        } else if (diff > units.week) {
            humanized = format(Math.floor(diff / units.week), "week");
        } else {
            var pieces = [], num, consider = ["day", "hour", "minute", "second"], measure;

            for (var i = 0, l = consider.length; i < l; ++i) {
                measure = units[consider[i]];

                if (diff > measure) {
                    num = Math.floor(diff / measure);
                    diff = diff - (num * measure);
                    pieces.push(format(num, consider[i]));
                }
            }

            humanized = (pieces.length == 1 ? pieces[0] :
                pieces.slice(0, pieces.length - 1).join(", ") + " and " +
                pieces[pieces.length - 1]);
        }

        return future ? "in " + humanized : humanized + " ago";
    };

}());

