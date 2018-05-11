//Immediate invoked function expression Design Pattern: IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
        }
        userService.createUser(user).then(findAllUsers);
    }

    function renderUsers(users) {
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);
            clone.find('.username')
                .html(user.username).
            tbody.append(clone);
        }
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');
        userService.deleteUser(userId);
    }

    function editUser(event) {
        console.log(event);
    }
})();