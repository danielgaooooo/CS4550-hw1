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
        findAllUsers();
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
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
        clone.find('.edit').click(selectUser);
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
            clone.find('.edit').click(selectUser);
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

    function selectUser(event) {
        console.log(event).then(updateUser);
    }

    function updateUser(user) {
        console.log('hey what\'s up');
    }
})();