//Immediate invoked function expression Design Pattern: IIFE
(function () {

    jQuery(main);

    var editing = false;
    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);
        findAllUsers();
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userService.createUser(user).then(findAllUsers);
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function findUserById(id) {
        userService.findUserById(id).then(renderUser);
    }

    function renderUser(user) {
        var clone = template.clone();
        clone.attr('id', user.id);
        clone.find('.delete').click(deleteUser);
        clone.find('.edit').click(updateUser);
        clone.find('.username')
            .html(user.username);
        clone.find('.firstName')
            .html(user.firstName);
        clone.find('.lastName')
            .html(user.lastName);
        clone.find('.role')
            .html(user.role);
        clone.attr('class', 'val');
        tbody.append(clone);
        emptyFields();
    }

    function renderUsers(users) {
        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(updateUser);
            clone.find('.username')
                .html(user.username);
            clone.find('.firstName')
                .html(user.firstName);
            clone.find('.lastName')
                .html(user.lastName);
            clone.find('.role')
                .html(user.role);
            clone.attr('class', 'val');
            tbody.append(clone);
        }
        emptyFields();
    }

    function emptyFields() {
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');
        userService.deleteUser(userId).then(findAllUsers);
    }

    function updateUser(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent();
        if (!editing) {
            userId.find('td').each(function () {
                this.contentEditable = true;
            });
            userId.find('.edit').html('Confirm');
            editing = true;
        } else {
            var newUsername;
            var newFirst;
            var newLast;
            var newRole;
            userId.find('td').each(function () {
                this.contentEditable = false;
            });

            /*
            newUsername = userId.find('.username').val();
            newFirst = userId.find('.firstName').val();
            newLast = userId.find('.lastName').val();
            newRole = userId.find('.role').val();
            var newUser = new User();
            newUser.setUsername(newUsername);
            newUser.setFirstName(newFirst);
            newUser.setLastName(newLast);
            newUser.setRole(newRole);
            userService.updateUser(parseInt(userId), newUser);
            */

            userId.find('.edit').html('Edit');
            editing = false;
        }
    }
})();