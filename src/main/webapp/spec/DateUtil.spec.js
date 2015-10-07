describe("dateUtil", function() {

    var second = 1000; //in milliseconds
    var minute = 60 * second;
    var hour = 60 * minute;
    var day = 24 * hour;
    var week = 7 * day;

    it("shall display 8 day difference as '1 week ago'", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var date8DaysAgo = new Date(now - 8 * day);
        expect(dateUtil.durationAsText(date8DaysAgo, now)).toBe("1 week ago");
    });

    it("shall display difference with days, hours, minutes and seconds", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var date = new Date(now - (3 * day + 2 * hour + 16 * minute + 10 * second));
        expect(dateUtil.durationAsText(date, now)).toBe("3 days, 2 hours, 16 minutes and 10 seconds ago");
    });

    it("shall display differences into the future", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var dateOneDayFromNow = new Date(now.getTime() + 1 * day);

        expect(dateUtil.durationAsText(dateOneDayFromNow, now)).toBe("in 24 hours");
    });
});

