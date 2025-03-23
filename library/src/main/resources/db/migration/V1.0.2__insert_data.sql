INSERT INTO author (name, image, bio)
VALUES (N'Nam Cao', N'https://example.com/images/nam_cao.jpg',
        N'Nam Cao là nhà văn hiện thực phê phán nổi bật của Việt Nam, với các tác phẩm như "Chí Phèo", "Lão Hạc", phản ánh sâu sắc số phận con người nghèo khổ.'),
       (N'Ngô Tất Tố', N'https://example.com/images/ngo_tat_to.jpg',
        N'Ngô Tất Tố là nhà văn, nhà báo nổi tiếng, tác phẩm tiêu biểu là "Tắt đèn", phản ánh hiện thực xã hội nông thôn Việt Nam trước Cách mạng.'),
       (N'Tô Hoài', N'https://example.com/images/to_hoai.jpg',
        N'Tô Hoài là nhà văn nổi tiếng với "Dế Mèn phiêu lưu ký", có văn phong nhẹ nhàng, sâu sắc, gần gũi với thiếu nhi.'),
       (N'Nguyễn Nhật Ánh', N'https://example.com/images/nguyen_nhat_anh.jpg',
        N'Nguyễn Nhật Ánh là nhà văn viết cho tuổi học trò, nổi tiếng với nhiều tác phẩm như "Mắt biếc", "Cho tôi xin một vé đi tuổi thơ".'),
       (N'Dale Carnegie', N'https://example.com/images/dale_carnegie.jpg',
        N'Dale Carnegie là tác giả Mỹ nổi tiếng với sách phát triển bản thân như "Đắc nhân tâm", giúp con người cải thiện giao tiếp và thành công trong cuộc sống.'),
       (N'Rosie Nguyễn', N'https://example.com/images/rosie_nguyen.jpg',
        N'Rosie Nguyễn là tác giả trẻ người Việt, nổi tiếng với cuốn "Tuổi trẻ đáng giá bao nhiêu", truyền cảm hứng sống và khám phá bản thân.');

INSERT INTO book (title, genre, quantity, price, status, image, author_id)
VALUES (N'Chí Phèo', N'Truyện ngắn', 50, 45000, N'Còn hàng',
        N'/api/images/view/54be3547-247e-43c1-94a7-43fc374f531b_chi-pheo_72e3f1370e484cf49b0fc94ee4ce0f80_master.webp',
        1),
       (N'Lão Hạc', N'Truyện ngắn', 40, 40000, N'Còn hàng',
        N'/api/images/view/8a2e8c75-6926-45a8-b9b5-8ef16d8ba2ff_truyen-ngan-lao-hac-nam-cao.jpg', 1),
       (N'Đời thừa', N'Truyện ngắn', 30, 42000, N'Còn hàng',
        N'/api/images/view/08599169-6a00-49bf-b746-5e9165b303a7_b083654aff109fc3ca83f27414bf4551.jpeg', 1),

       (N'Tắt đèn', N'Tiểu thuyết', 60, 48000, N'Còn hàng',
        N'/api/images/view/0ef26967-c965-449b-8ca6-89c1535f4e69_bia_truoc_08_b4497226ce524fd79a1d81b9086b99a9_master.jpg',
        2),
       (N'Việc làng', N'Truyện ngắn', 35, 39000, N'Còn hàng',
        N'/api/images/view/d0bddec4-4a49-4110-9b15-d016cd57d32e_image_195509_1_41310.webp', 2),

       (N'Dế Mèn phiêu lưu ký', N'Truyện thiếu nhi', 100, 52000, N'Còn hàng',
        N'/api/images/view/af879d81-4142-477a-adc9-102d2208ad74_unnamed.png',
        3),
       (N'O chuột', N'Truyện ngắn', 25, 37000, N'Còn hàng',
        N'/api/images/view/c1b4e209-0c57-409f-b2ff-1680769d1034_o-chuot.jpg', 3),

       (N'Mắt biếc', N'Tiểu thuyết', 80, 68000, N'Còn hàng',
        N'/api/images/view/2a3ab785-4a1f-4eec-a91b-b1be69bac437_20240516_xkxFxB9jCh.jpeg', 4),
       (N'Cho tôi xin một vé đi tuổi thơ', N'Tiểu thuyết', 90, 70000, N'Còn hàng',
        N'/api/images/view/0624442f-eae4-4e15-82e4-d1cee2ccad65_nxbtre_full_08142018_091438.jpg', 4),
       (N'Tôi thấy hoa vàng trên cỏ xanh', N'Tiểu thuyết', 75, 75000, N'Còn hàng',
        N'/api/images/view/490db6d0-5391-4f1b-b1d6-35b48a92a4af_NXBTreStoryFull_02482010_104821.jpg', 4),

       (N'Đắc nhân tâm', N'Kỹ năng sống', 120, 99000, N'Còn hàng',
        N'/api/images/view/5fb76e4c-4883-4a65-9057-48a0eab2a034_dacnhantam86.jpg', 5),
       (N'Quẳng gánh lo đi và vui sống', N'Tư duy tích cực', 100, 88000, N'Còn hàng',
        N'/api/images/view/38e00d71-358c-4f43-ab4b-823be9de39a2_z2602650248500_93e18e312805d6583448b61f4007a67e_1.webp',
        5),

       (N'Tuổi trẻ đáng giá bao nhiêu', N'Sách truyền cảm hứng', 110, 76000, N'Còn hàng',
        N'/api/images/view/073fe794-c788-4adf-aa71-ca7832589d04_64b2eaa5b859d8ba1412f1b350c3e128.jpg', 6),
       (N'Mình nói gì khi nói về hạnh phúc', N'Tư duy sống', 55, 72000, N'Còn hàng',
        N'/api/images/view/2c9c570a-1c5a-4e94-9e36-b89423372873_39083808.jpg', 6),
       (N'Bức xúc không làm ta vô can', N'Tư duy xã hội', 45, 69000, N'Còn hàng',
        N'/api/images/view/60926694-c9f3-421d-a180-85b7ef8c5d0d_27413510.jpg', 6);
;

