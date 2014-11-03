use std::io::{TcpListener, Acceptor, Listener};

fn main() {
    let listener = TcpListener::bind("127.0.0.1", 12345);

    // bind the listener to the specified address
    let mut acceptor = listener.listen();

    for stream in acceptor.incoming() {
        match stream {
            Err(_e) => { /* connection failed */ }
            Ok(mut stream) => spawn(proc() {
                stream.write(b"HTTP/1.0 200 OK
Content-Length: 12

Hello world!
").unwrap();
        })
    }
    }
}
