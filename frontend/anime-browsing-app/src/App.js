import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import AnimeList from './components/AnimeList';
import AnimeListHeading from './components/AnimeListHeading';
import AddToFavourite from './components/AddToFavourites';
import { useQuery, gql } from "@apollo/client";
import RemoveFavourites from './components/RemoveFavourite';


const GET_ANIME = gql`
    query GetAnime($page: Int, $pageSize: Int) {
        anime(page: $page, pageSize: $pageSize) {
            items {
                uid
                title
                img_url
            }
            totalCount
            page
            pageSize
            totalPages
        }
    }
`
function GetAnimeRequest() {
    const page = 6;
    const pageSize = 10;
    const { loading, error, data } = useQuery(GET_ANIME, {
        variables: {page, pageSize}
    });
    return {loading ,error, data};
}



const App = () => {
  const response = GetAnimeRequest();
  const [favourites, setFavourites] = useState([]);

  const addFavouriteAnime = (anime) => {
    const newFavouritesList = [...favourites, anime];
    setFavourites(newFavouritesList);
  };

  const removeFavouriteAnime = (anime) => {
    const newFavouritesList = favourites.filter(
      (favourite) => favourite.uid !== anime.uid
    );
    setFavourites(newFavouritesList);
  }

  return (
      <div className='container-fluid movie-app'>
        <div className='row d-flex align-items-center mt-4 mb-4'>
          <AnimeListHeading heading='Anime'></AnimeListHeading>
        </div>
        <div className='row'>
          <AnimeList response={response} favouriteComponent={AddToFavourite} handleFavouritesClick={addFavouriteAnime}></AnimeList>
        </div>
        <div className='row d-flex align-items-center mt-4 mb-4'>
                <AnimeListHeading heading='Favourites' />
            </div>
            <div className='row'>
                <AnimeList favourites={favourites} favouriteComponent={RemoveFavourites} handleFavouritesClick={removeFavouriteAnime}/>
            </div>

      </div>    
  )
}


export default App;
