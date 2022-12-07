INSERT INTO users (id, email, first_name, image_url, is_active, last_name, password)
VALUES (1, 'gosho@abv.bg', 'Gosho', null, 1, 'Goshev', '0d8672212991c4922bac59816021e17847543112f53d5f201ce0d9b64d84b1238c3bf835d849e3dd');

INSERT INTO brands (id, name)
VALUES (1, 'Ford'),
       (2, 'Toyota');

INSERT INTO models (id, name, category, start_year, end_year, brand_id, imageurl)
VALUES (1, 'Fiesta', 'CAR', 1976, null, 1,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1200px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
        (2, 'Escort', 'CAR', 1968, 2000, 1,
    'https://upload.wikimedia.org/wikipedia/commons/3/39/Ford_Escort_RS2000_MkI.jpg'),
        (3, 'Yaris', 'CAR', 1999, null, 2,
         'https://upload.wikimedia.org/wikipedia/commons/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg');