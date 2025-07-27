import React from 'react';
import Post from './Post';

class Posts extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            posts: [],
            loading: true,
            error: null
        };
    }

    loadPosts(){
        this.setState({ loading: true, error: null });
        
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const posts = data.map(post => new Post(post.id, post.title, post.body));
                this.setState({ posts: posts, loading: false, error: null });
            })
            .catch(error => {
                console.error('Error fetching posts:', error);
                this.setState({ 
                    loading: false, 
                    error: error.message,
                    posts: this.getFallbackPosts()
                });
            });
    }

    componentDidMount(){
        this.loadPosts();
    }

    componentDidCatch(error, errorInfo){
        alert('An error occurred in the Posts component: ' + error.message);
        console.error('Error details:', error, errorInfo);
        this.setState({ 
            error: error.message,
            loading: false,
            posts: this.getFallbackPosts()
        });
    }

    render(){
        const { posts, loading, error } = this.state;

        if (loading) {
            return (
                <div>
                    <h1>Blog Posts</h1>
                    <p>Loading posts...</p>
                </div>
            );
        }

        return (
            <div>
                <h1>Blog Posts</h1>
                {error && (
                    <div style={{ 
                        backgroundColor: '#fff3cd', 
                        border: '1px solid #ffeaa7', 
                        padding: '10px', 
                        margin: '10px 0',
                        borderRadius: '4px'
                    }}>
                        <strong>Note:</strong> Could not fetch posts from external API ({error}). 
                        Showing sample posts instead.
                    </div>
                )}
                {posts.map(post => (
                    <div key={post.id} style={{ 
                        marginBottom: '20px', 
                        padding: '15px', 
                        border: '1px solid #ddd',
                        borderRadius: '5px'
                    }}>
                        <h2 style={{ color: '#333', marginBottom: '10px' }}>{post.title}</h2>
                        <p style={{ lineHeight: '1.6', color: '#666' }}>{post.body}</p>
                    </div>
                ))}
            </div>
        );
    }
}

export default Posts;