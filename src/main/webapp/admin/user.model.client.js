function User (id, username, password, firstname, lastname, role) {

    this.id = id;
    this.username = username;
    this.password= password;
    this.firstName = firstname;
    this.lastName =lastname;
    this.role = role;

    this.getId = getId;
    this.setId = setId;
    this.getUsername = getUsername;
    this.setUsername = setUsername;
    this.getPassword = getPassword;
    this.setPassword = setPassword;
    this.getFirstname = getFirstname;
    this.setFirstname = setFirstname;
    this.getLastname =  getLastname;
    this.SetLastname =  setLastname;
    this.getRole = getRole;
    this.setRole = setRole;

    function getId() {
        return this.id;
    }
    function getUsername() {
        return this.username;
    }
    function getPassword() {
        return this.password;
    }
    function getFirstname() {
        return this.firstName;
    }
    function getLastname() {
        return this.lastName;
    }
    function getRole() {
        return this.role;
    }

    function setId() {
        this.id = id;
    }
    function setUsername() {
        this.username = id;
    }
    function setPassword() {
        this.password = id;
    }
    function setFirstname() {
        this.firstName = id;
    }
    function setLastname() {
        this.lastName = id;
    }
    function setRole() {
        this.role = role;
    }

    return this;

};

