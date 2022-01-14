import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ListTastComponent from './components/ListTaskComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import AddTaskComponent from './components/AddTaskComponent'

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent/>
        <div className='container'>
          <Routes>
            <Route path='/' element={<ListTastComponent/>}></Route>
            <Route path='/tasks' element={<ListTastComponent/>}></Route>
            <Route path='/add-task' element={<AddTaskComponent/>}></Route>
            <Route path='/edit-task/:id' element={<AddTaskComponent/>}></Route>
          </Routes>
        </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;
