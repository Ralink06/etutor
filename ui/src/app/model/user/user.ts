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

export class LoggedUser {

  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: Date;
}
