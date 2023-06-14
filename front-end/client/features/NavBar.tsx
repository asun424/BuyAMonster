import { ReactNode } from "react"

const NavBar = () => {

    const categories = ["sub1", "sub2", "sub3"]

    const mapCategories = (categories: string[]): ReactNode => {
        return categories.map((category: string, index: number): ReactNode => <div key={index}>{category}</div>)
    }


    return (
        <div className=" h-20 flex flex-col items-center">
            <div className="h-2/3 w-full flex justify-evenly items-center border-2">
                <div className="mx-6">
                    Mainpage
                </div>
                <div className="flex mr-6">
                    <div>
                        <input className="border-2 w-[500px]"/>
                        <button className="border-2 w-24">Search</button>
                    </div>
                </div>
                <div className="flex justify-between w-48">
                    <a href="/login">
                        Login
                    </a>
                    <a href="/cart">
                        My Cart
                    </a>
                </div>
            </div>
            <div className="h-1/3 w-full flex justify-evenly border-2">
                {mapCategories(categories)}
            </div>
        </div>
    )
}

export default NavBar