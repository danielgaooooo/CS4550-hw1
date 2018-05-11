function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.deleteUser = deleteUser;
    this.url = 'http://localhost:8080/api/user';
    this.login = login();
    var self = this;

    function login(username, password) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            heads: {
                'content-type': 'application/json'
            }
        })
    }

    function updateUser(userId, user) {

    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
    }

    function findAllUsers() {
        return fetch(self.url).then(function (response) {
            return response.json()
        });
    }

    function findUserById(id) {
        return fetch(self.url + '/' + id).then(function(response) {
            return response.json();
        })
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }
}