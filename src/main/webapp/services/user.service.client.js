function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url='http://localhost:8080/api/user';

    var self = this;

    function createUser(username) {
        return fetch(this.url, {
            method: 'post',
            body: {"id":123,"username":"abc","password":null,"firstName":"def","lastName":"ghi"},
            headers: {
                'content-type': 'application/json'
            }
        })
    }
    function findAllUsers() {
        return fetch(this.url)
            .then(function (response) {
                return response.json();
            });
    }
    function findUserById(userId) {
        return(fetch(this.url + '/' + userId)
            .then(function (response) {
                return response.json();
            }))

    }
    function updateUser(userId, user, callback) {

    }
    function deleteUser(userId, callback) {

    }
}
