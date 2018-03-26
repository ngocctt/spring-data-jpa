$(document).ready(function () {
    $("#btn-search").on("click", function () {
        var firstName = $("#firstName")[0].value;
        var lastName = $("#lastName")[0].value;
        var status = $("#status")[0].value;
        var url = "/spring-jpa-demo/salespeople/search?";
        if (firstName != "" || firstName != null) {
            url += "firstName=" + firstName;
        }
        if (lastName != null || lastName != "") {
            url += "&lastName=" + lastName;
        }
        if (status != null || status != "") {
            url += "&status=" + status;
        }
        location.href = url;
    });

    $("#next").on("click", function () {
        var page = location.search.split("&")[0].split("=")[1];
        var nextPage;
        if (page != null || page != undefined) {
            nextPage = parseInt(location.search.split("&")[0].split("=")[1]) + 1;
        }
        location.href = "/spring-jpa-demo/salespeople?page=" + nextPage + "&size=5&sort=salesPersonId";
    });

    $("#prev").on("click", function () {
        var page = location.search.split("&")[0].split("=")[1];
        var prevPage;
        if (page != null || page != undefined) {
            prevPage = parseInt(location.search.split("&")[0].split("=")[1]) - 1;
        }
        location.href = "/spring-jpa-demo/salespeople?page=" + prevPage + "&size=5&sort=salesPersonId";
    });
});