import React from "react";



const AnimeList = (props) => {
    
    const FavouriteComponent = props.favouriteComponent;
    if (props.response?.loading) return <p>Loading...</p>;
    if (props.response?.error) return <p>Error: {props.response.error.message}</p>;
    const animes = props.response?.data?.anime?.items || props.favourites || [];

    return (
        <>
            {animes.map((anime, index) => (
                <div className="image-container d-flex justify-content-start m-3">
                    <img src={anime.img_url} alt='anime'></img>
                    <div 
                    onClick={() => props.handleFavouritesClick(anime)}
                    className="overlay d-flex align-items-center justify-content-center">
                        <FavouriteComponent/>
                    </div>
                </div>
                
            ))}
        </>

    );
};
export default AnimeList;