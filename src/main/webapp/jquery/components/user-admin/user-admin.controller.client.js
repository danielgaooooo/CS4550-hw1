//Immediate invoked function expression Design Pattern: IIFE
/*
(function () {
    alert("Hello again!");
})();
*/

(function() {

    jQuery(main);

    function main() {
        var h1 = jQuery('h1#title');
        h1.css('color', 'red');
        h1.html('User Administration changed');

        var red = $('.red');
        var green = $('.green');
        var blue = $('.blue');

        red.css('color', 'white');
        red.css('background-color', 'red');

        var tr = $('.template');

        var users = [{username: 'bob'}, {username: 'charlie'}];
        var tbody = $('tbody');

        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = tr.clone();
            clone.find('.username').html(user.username);
            tbody.append(clone);
        }
    }
})();