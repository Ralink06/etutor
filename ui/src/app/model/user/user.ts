export interface CreateUser {

  firstName: string;
  lastName: string;
  password: string;
  email: string;
  dateOfBirth: Date;
}

export interface UserLogin {
  password: string;
  username: string;
}
