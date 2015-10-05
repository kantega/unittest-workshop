describe("dateUtil", function() {

    it("shall display 8 day difference as '1 week ago'", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var date = new Date(now - 8 * 24 * 60 * 60 * 1000);
        expect(dateUtil.durationAsText(date, now)).toBe("1 week ago");
    });

    it("shall display difference with days, hours, minutes and seconds", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var date = new Date(now - (3 * 24 * 60 * 60 * 1000) - (2 * 60 * 60 * 1000) - (16 * 60 * 1000) - (10 * 1000));
        expect(dateUtil.durationAsText(date, now)).toBe("3 days, 2 hours, 16 minutes and 10 seconds ago");
    });

    it("shall display differences into the future", function () {
        var dateUtil = new DateUtil();
        var now = new Date();
        var date = new Date(now.getTime() + 24 * 60 * 60 * 1000);

        expect(dateUtil.durationAsText(date, now)).toBe("in 24 hours");
    });

});

