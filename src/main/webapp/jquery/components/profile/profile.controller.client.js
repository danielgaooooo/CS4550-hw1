(function() {
    $(init);

    var $staticEmail;
    var $firstName;
    var $lastName;
    var $updateBtn;

    var userService = new UserServiceClient();

    function init() {
        $staticEmail = $("#staticEmail");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $updateBtn = $("#updateBtn").click(updateUser);

        findUserById(12);
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $staticEmail.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }
})();