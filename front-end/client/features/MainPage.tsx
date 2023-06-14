import { ReactElement } from "react"

const MainPage = () : ReactElement => {
    return (
        <div className="h-full w-full flex flex-col justify-start items-center">
            <div className="w-full h-1/4 border-2">
                banner goes here               
            </div>
            <div className="w-full h-3/4 border-2">
                product display goes here
            </div>
        </div>
    )
}

export default MainPage