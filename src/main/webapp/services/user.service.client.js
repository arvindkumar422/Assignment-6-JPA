function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.findUsersByField = findUsersByField;
    this.url='https://assignment-6-neu.herokuapp.com/api/user';

    var self = this;

    function createUser(userObj){
        return fetch('/create', {
            method: 'post',
            body: JSON.stringify(userObj),
            headers: {
                'content-type': 'application/json'
            }
        })
    }
    function findAllUsers() {
        return fetch(this.url)
            .then(function (response) {
                console.log(response);
                return response.json();
            });
    }
    function findUserById(userId) {
        //console.log(userId);
        return(fetch(this.url + '/' + userId)
            .then(function (response) {
                return response.json();
            }));

    }

    function updateUser(userId, user) {
        return(fetch(this.url + '/' + userId,
            {
                method: 'put',
                body: JSON.stringify(user),
                headers: {
                    'content-type': 'application/json'
                }
            }));

    }
    function deleteUser(userId) {
        return fetch(this.url +'/'+ userId, {
            method: 'delete'
        })
    }

    function findUsersByField(usernameStr, firstNameStr, lastNameStr, roleStr) {

        var temp_url = 'https://assignment-6-neu.herokuapp.com/api/user';
        if (usernameStr.length != 0) {
            temp_url += '/' + usernameStr;
        }
        else {
            temp_url += '/un=*';
        }temp_url
        if (firstNameStr.length != 0) {
            temp_url += '/' + firstNameStr;
        }
        else {
            temp_url += '/fn=*';
        }
        if (lastNameStr.length != 0) {
            temp_url += '/' + lastNameStr;
        }
        else {
            temp_url += '/ln=*';
        }
        if (roleStr.length != 0) {
            temp_url += '/' + roleStr;
        }
        else {
            temp_url += '/rl=*';
        }
        console.log(temp_url);
        return fetch(temp_url)
            .then(function (response) {
                console.log(response);
                return response.json();
            });

    }
}
