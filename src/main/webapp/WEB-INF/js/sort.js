$(document).ready(function () {
    $('#btnSortAsc').click(function () {

        if (window.location.href.indexOf('search') !== -1) {

            window.location.href = window.location.pathname + window.location.search;

        } else {

            if (window.location.search.indexOf('sortType') !== -1) {
                window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('?')) + "?sortType=asc";
            }
            else {
                window.location.href = window.location.pathname + window.location.search + "?sortType=asc";
            }
        }
    });

    $('#btnSortDesc').click(function () {
        if (window.location.href.indexOf('search') !== -1) {
            window.location.href = window.location.pathname + window.location.search;
        } else {
            if (window.location.search.indexOf('sortType') !== -1) {
                window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('?')) + "?sortType=desc";
            }
            else {
                window.location.href = window.location.pathname + window.location.search + "?sortType=desc";
            }
        }
    });

    $('#btnSortClear').click(function () {

        if (window.location.href.indexOf('search') !== -1) {
            window.location.href = window.location.pathname + window.location.search;
        } else {
            if (window.location.search.indexOf('sortType') !== -1) {
                window.location.href = window.location.pathname + window.location.search.substring(0, window.location.search.indexOf('?'));
            } else {
                window.location.href = window.location.pathname + window.location.search;
            }
        }
    });
})

