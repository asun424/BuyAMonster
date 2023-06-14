export interface productObject {
    id: string,
    name: string, 
    common_locations: string[],
    description: string,
    drops: string[],
    price: BigInteger,
    image: string
}

export interface loginSignUpObject {
    username: string,
    password: string,
    email?: string,
    method?: string
}

export interface tokenData {
    token: string
}

export interface User {
    id: number,
    username: String, 
    email: String,
}

export interface IAuthContext {
    loginSignUp: (loginSignupObj: loginSignUpObject) => Promise<void>,
    logOut: () => void,
    user: null | User,
    error: null | string
}