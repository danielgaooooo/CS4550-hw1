(function() {
    $(init);

    var userService = new UserServiceClient();

    function init() {
        findUserById(12);
    }

    function findUserById(userId) {
        userService.findUserById(userId).then(renderUser);
    }

    function renderUser(user) {

    }
})();