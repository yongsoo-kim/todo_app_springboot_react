import React from "react";
import Todo from "./Todo";
import AddTodo from "./AddTodo";
import './App.css'
import {call, signout} from "./service/ApiService";
import {AppBar, Toolbar, Typography, Button, Grid, Container, List, Paper} from "@material-ui/core";


class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            loading: true,
        };
    }

    add = (item) => {
        call("/todo", "POST", item).then((response) =>
            this.setState({items: response.data})
        );
        // const thisItems = this.state.items;
        // item.id = "ID-" + thisItems.length;
        // item.done = false;
        // thisItems.push(item);
        // this.setState({items: thisItems});
        // console.log("items : ", this.state.items)
    }

    update = (item) => {
        call("/todo", "PUT", item).then((response) => {
            this.setState({items: response.data})
        });
    };

    delete = (item) => {
        call("/todo", "DELETE", item).then((response) =>
            this.setState({items: response.data})
        );
        // const thisItems = this.state.items;
        // console.log("Before Update Items: ", this.state.items)
        // const newItems = thisItems.filter(e => e.id !== item.id)
        // this.setState({items: newItems}, () => {
        //     console.log("Update Items: ", this.state.items)
        // });
    }

    componentDidMount() {
        call("/todo", "GET", null).then((response) =>
            this.setState({items: response.data, loading: false}));
    }




    render() {

        var todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16}}>
                <List>
                    {this.state.items.map((item, idx) => (
                        <Todo
                            item={item}
                            key={item.id}
                            delete={this.delete}
                            update={this.update}
                        />
                    ))}
                </List>
            </Paper>
        );

        var navigationBar = (
            <AppBar position="static">
                <Toolbar>
                    <Grid item>
                        <Typography variant="h6">오늘의 할일</Typography>
                    </Grid>
                    <Grid>
                        <Button color="inherit" onClick={signout}>
                            로그아웃
                        </Button>
                    </Grid>
                </Toolbar>
            </AppBar>
        );

        var todoListPage =(
            <div className="App">
                {navigationBar}
                <Container maxWidth="md">
                    <AddTodo add={this.add}/>
                    <div className="TodoList">{todoItems}</div>
                </Container>
            </div>
        );


        var loadingPage = <h1> 로딩중...</h1>

        var content = loadingPage;

        if (!this.state.loading) {
            content = todoListPage;
        }


        return (
            <div className="App">
                {content}
            </div>
        );
    }
}

export default App;

// import logo from './logo.svg';
// import './App.css';
//
// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }
//
// export default App;
