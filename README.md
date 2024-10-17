<h1> LẬP TRÌNH MẠNG <h1>


<h2>1. Mô tả đề tài</h2>  
<ol>
    <li>Người chơi tham gia vào phòng chơi hoặc phòng chờ trực tuyến. Một phòng chơi có thể chứa nhiều người chơi.</li>
    <li>Một người chơi sẽ được chọn để vẽ hoặc mô tả một từ hoặc cụm từ bí mật. Trong trường hợp vẽ, họ sẽ sử dụng bút vẽ trực tuyến để vẽ hình minh họa cho từ đó. </li>
    <li>Các người chơi khác trong phòng chờ sẽ cố gắng đoán từ hoặc cụm từ dựa trên hình vẽ hoặc mô tả. Họ sử dụng gợi ý và bảng từ vựng để đoán. </li>
    <li>Người chơi có thời gian giới hạn để đoán. Nếu họ đoán đúng, họ nhận điểm. </li>
    <li>Sau khi thời gian giới hạn kết thúc hoặc khi có người đoán đúng, người vẽ hoặc mô tả tiết lộ từ hoặc cụm từ mục tiêu. </li>
    <li>Cuối cùng, điểm số được tính toán và bảng xếp hạng hiển thị người chơi xuất sắc nhất trong trò chơi. </li>
</ol>

<h2>2. Thông tin nhóm:</h2>
Bùi Hoàng Vinh - B20DCCN736 <br />
Nguyễn Đức Anh - B20DCCN057 <br />
Nguyễn Thanh Trúc - B20DCCN693 <br />
<a href="https://docs.google.com/spreadsheets/d/12DA6hUDR2D5mtfSBN6aYQ1YK7iiFlCcjrbf9VVIjuIY/edit?hl=vi#gid=0"> JNP Project Branch Notes </a> <br />

<h2>3. Công nghệ và ngôn ngữ sử dụng:</h2>
<h3>Đối với phiên bản Desktop app</h3>
<p> Xử lý logic: </p>
Sử dụng ngôn ngữ lập trình Java với các thư viện chuẩn <br>
<br />
<p> Xử lý giao diện: </p>
Sử dụng Java Swing (GUI) <br>
<br />
<h3>Đối với phiên bản Web</h3>
<p> Xử lý logic: </p>
Sử dụng NodeJs là server socket <br/>
Sử dụng Java Web là server web (server side) <br/>
<br />
<p> Xử lý giao diện: </p>
Sử dụng HTML, CSS, JavaScript cho phiên bản Web <br>

<h2>4. Mô tả kiến trúc, chức năng chính: </h2>
Project chia thành 2 phần xử lý: Client và Server
Ở phiên bản Desktop app, Client và Server giao tiếp thông qua TCP Socket, gửi và nhận thông tin để thực hiện chơi game:<br>
- Gửi tọa độ điểm ảnh để gửi thông tin nét vẽ <br />
- Gửi hành động như đổi màu, tẩy xóa <br />
- Gửi tin nhắn dự đoán từ <br />
<h3>Chức năng chính:</h3>
- Người chơi thực hiện vẽ trên giao diện game, các nét vẽ này sẽ được hiển thị trên màn hình giao diện của các người chơi khác<br/>
- Có bộ đếm thời gian cho 1 ván game<br/>
- Đưa ra gợi ý sau mỗi khoảng thời gian trong ván game (ví dụ: XIN CH_ _)<br/>
- Có phiên bản của ứng dụng trên web<br/>

<h2>Preview Giao diện</h2>
<h3>Giao diện phiên bản App sử dụng Swing</h3>
<p>Giao diện đăng nhập</p>
<img src = "https://github.com/jnp2018/g6_proj-736057693/assets/91203644/d98bf1f6-53c4-43f1-b080-0ba22915a5ce"> </img>
<p>Giao diện chơi game</p>
<img width="664" alt="image" src="https://github.com/jnp2018/g6_proj-736057693/assets/89853311/4bf8ab58-76c4-4cba-b787-05bcd478a260">
<br />
<h3>Giao diện phiên bản Web</h3>
<img width="960" alt="image" src="https://github.com/jnp2018/g6_proj-736057693/assets/89853311/58b9f96d-8c6c-4f57-aca5-12e3cf90e5a1">


<h2>Cài đặt môi trường:</h2>
<b>Đối với phiên bản Desktop app</b>, để thực hiện chạy chương trình cần cài đặt môi trường chạy Java với các thư viện chuẩn và thư viện Java Swing
<br/>
<b>Đối với phiên bản web</b>, cần cài đặt môi trường node js với thư viện express và websocket
<br>
npm install express
<br>
npm install websocket

