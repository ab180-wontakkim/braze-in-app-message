# Braze In-app message

## Getting started

### Step 1 : Download your `google-services.json` file

Go to your fire base console page and download your `google-services.json` and place `$rootProject/app` folder

![image](./images/google-services.png)

### Step 2 : Set your Braze dashboard data

Open `$rootProject/app/src/main/res/values/braze.xml` file and fill `com_appboy_api_key`, `com_appboy_custom_endpoint` and `com_appboy_firebase_cloud_messaging_sender_id` data

## How to reproduce the HTML overlay issues?

### Failure scenario

1. Send in-app message with following html code

``` html
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Single Page Modal</title>
    <!--
        Instructions:
        [ ] Copy this HTML into the Braze Dashboard IAM message composer
        [ ] Upload and swap out all images (eg: https://via.placeholder.com/450 -> your new image URL)
        [ ] Change the `href="#"` to `href="https://your-link" or `href="your://deeplink"`
        [ ] Change button text
    -->
    <style>
        /* fonts */
        @font-face {
            font-family: 'Sailec Bold';
            src: url('https://appboy-images.com/appboy/communication/assets/font_assets/files/5f2d540126e78f1669f53229/original.otf?1596806145') format('truetype');
        }

        @font-face {
            font-family: 'Sailec Normal';
            src: url('https://appboy-images.com/appboy/communication/assets/font_assets/files/5f2d5401ff0a7415251998d6/original.otf?1596806145') format('truetype');
        }

        :root {
            --header-font-stack: 'Sailec Bold', helvetica, arial, sans-serif;
            --body-font-stack: 'Sailec Normal', helvetica, arial, sans-serif;
        }

        /* Box sizing rules */
        *,
        *::before,
        *::after {
            box-sizing: border-box;
        }

        /* Remove default padding */
        ul[class],
        ol[class] {
            padding: 0;
        }

        /* Remove default margin */
        body,
        h1,
        h2,
        h3,
        h4,
        p,
        ul[class],
        ol[class],
        li,
        figure,
        figcaption,
        blockquote,
        dl,
        dd {
            margin: 0;
        }

        /* Set core body defaults */
        body {
            min-height: 100vh;
            scroll-behavior: smooth;
            text-rendering: optimizeSpeed;
            line-height: 1.5;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        /* Remove list styles on ul, ol elements with a class attribute */
        ul[class],
        ol[class] {
            list-style: none;
        }

        /* A elements that don't have a class get default styles */
        a:not([class]) {
            text-decoration-skip-ink: auto;
        }

        /* Make images easier to work with */
        img {
            max-width: 100%;
            display: block;
        }

        /* Natural flow and rhythm in articles by default */
        article>*+* {
            margin-top: 1em;
        }

        /* Inherit fonts for inputs and buttons */
        input,
        button,
        a,
        textarea,
        select {
            font: inherit;
        }

        button,
        a {
            border: none;
            background-color: transparent;
            text-decoration: none;
        }

        .overlay {
            display: flex;
            position: fixed;
            top: 0;
            left: 0;
            height: 100vh;
            width: 100vw;
            background-color: rgba(0, 0, 0, 0.7);
            z-index: 99999;
        }

        .modal {
            display: flex;
            height: 450px;
            max-width: 900px;
            flex-basis: 900px;
            margin: auto;
        }

        .modal__image {
            height: 450px;
            max-width: 450px;
            flex-basis: 450px;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        .modal__content {
            display: flex;
            flex-direction: column;
            position: relative;
            height: 450px;
            max-width: 450px;
            flex-basis: 450px;
            padding: 60px 40px 40px;
            background-color: rgb(255, 255, 255);
        }

        .modal__cross {
            position: absolute;
            top: 20px;
            right: 20px;
            height: 34px;
            width: 34px;
            cursor: pointer;
        }

        .modal__title {
            width: 300px;
            margin-bottom: 10px;
            font-size: 36px;
            line-height: 1.2;
            color: #101B24;
            font-family: var(--header-font-stack);
        }

        .modal__sub-title {
            margin-bottom: 10px;
            font-family: var(--header-font-stack);
            font-size: 16px;
            color: #76848C;
        }

        .modal__text {
            font-family: var(--body-font-stack);
            font-size: 14px;
            color: #101B24;
            line-height: 1.5;
        }

        .modal__button-wrap {
            margin-top: auto;
            justify-content: space-around;
            display: flex;
        }

        .modal__btn {
            font-family: var(--header-font-stack);
            font-weight: 700;
            color: inherit;
            min-height: 50px;
            align-items: center;
            display: flex;
            justify-content: center;
            min-width: 140px;
            text-align: center;
        }

        .modal__btn--main {
            margin-left: 10px;
            padding: 8px 16px;
            background-color: #0099AE;
            color: rgb(255, 255, 255);
            font-size: 14px;
        }

        .modal__btn--secondary {
            padding: 8px 16px;
            border: 1px solid #A8B3B8;
            font-size: 14px;
        }

        .modal__cross-mobile {
            display: none !important;
        }

        /*Phones*/
        @media (max-width: 900px) {
            .modal {
                height: auto;
                max-width: 330px;
                flex-basis: 330px;
                margin: auto;
                display: flex;
                flex-direction: column;
                position: relative;
            }

            .modal__cross-mobile {
                border-radius: 50%;
                background: white;
                display: flex !important;
                justify-content: center;
                padding: 1px;
            }

            .modal__cross-web {
                display: none !important;
            }

            .modal__title,
            .modal__sub-title {
                margin-bottom: 8px;
            }

            .modal__title {
                font-size: 23px;
            }

            .modal__image {
                height: 330px;
                flex-basis: 330px;
            }

            .modal__content {
                display: flex;
                flex-direction: column;
                position: relative;
                height: auto;
                max-width: 330px;
                flex-basis: auto;
                padding: 24px 16px 24px 16px;
                background-color: rgb(255, 255, 255);
            }

            .modal__button-wrap {
                display: flex;
                margin-top: 24px;
                margin-left: 0px;
                justify-content: space-between;
            }

            .modal__btn {
                width: 140px;
                padding: 8px 8px;
            }

            .modal__carousel-toggle {
                background-color: transparent;
            }

            .modal__carousel-navigation.is-active {
                background-color: white;
            }
        }

        @media (orientation: landscape) and (max-width: 900px) {
            .modal {
                max-width: 660px;
                flex-basis: 660px;
            }

            .modal {
                display: flex;
                flex-direction: row;
                height: 330px;
                margin: auto;
            }

            .modal__cross {
                right: unset;
                left: 14px;
            }
        }

        @media (orientation: landscape) and (max-width: 569px) {
            .modal {
                max-width: 568px;
                flex-basis: 568px;
            }

            .overlay {
                overflow-y: scroll;
            }
        }

        @media (max-width: 320px) {
            .overlay {
                overflow-y: scroll;
            }
        }

        .image-1 {
            background-image: url('https://via.placeholder.com/450');
        }
    </style>
</head>

<body>
    <div class="overlay">
        <div class="modal">
            <button data-button-id="close" class="modal__cross modal__cross-mobile">
                <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                        d="M14.4175 16.8419L8.51467 10.9391C7.8452 10.2696 7.8452 9.18422 8.51467 8.51475C9.18414 7.84528 10.2696 7.84528 10.939 8.51475L16.8418 14.4176L22.7446 8.51475C23.4141 7.84528 24.4995 7.84528 25.169 8.51475C25.8385 9.18422 25.8385 10.2696 25.169 10.9391L19.2662 16.8419L25.169 22.7447C25.8385 23.4142 25.8385 24.4996 25.169 25.1691C24.4995 25.8386 23.4141 25.8386 22.7446 25.1691L16.8418 19.2663L10.939 25.1691C10.2696 25.8386 9.18414 25.8386 8.51467 25.1691C7.8452 24.4996 7.8452 23.4142 8.51467 22.7447L14.4175 16.8419Z"
                        fill="#A8B3B8" />
                </svg>
            </button>
            <div class="modal__image image-1"></div>
            <div class="modal__content">
                <button data-button-id="close" class="modal__cross modal__cross-web">
                    <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M14.4175 16.8419L8.51467 10.9391C7.8452 10.2696 7.8452 9.18422 8.51467 8.51475C9.18414 7.84528 10.2696 7.84528 10.939 8.51475L16.8418 14.4176L22.7446 8.51475C23.4141 7.84528 24.4995 7.84528 25.169 8.51475C25.8385 9.18422 25.8385 10.2696 25.169 10.9391L19.2662 16.8419L25.169 22.7447C25.8385 23.4142 25.8385 24.4996 25.169 25.1691C24.4995 25.8386 23.4141 25.8386 22.7446 25.1691L16.8418 19.2663L10.939 25.1691C10.2696 25.8386 9.18414 25.8386 8.51467 25.1691C7.8452 24.4996 7.8452 23.4142 8.51467 22.7447L14.4175 16.8419Z"
                            fill="#A8B3B8" />
                    </svg>
                </button>
                <h1 class="modal__title">This is where the modal title goes</h1>
                <h2 class="modal__sub-title">Sub head maybe here that is not too long</h2>
                <p class="modal__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                <div class="modal__button-wrap">
                    <a href="iam://secondary_clicked" class="modal__btn modal__btn--secondary" data-button-id="1">Secondary
                        Action</a>
                    <a href="iam://primary_clicked" class="modal__btn modal__btn--main" data-button-id="0">Primary
                        Action</a>
                </div>
            </div>
        </div>
    </div>

    <script>
        // **************** Problematic section ****************
        document.querySelectorAll('[data-button-id]').forEach(function (node) {
            node.addEventListener('click', function () {
                appboyBridge.logClick(node.dataset.buttonId);
                appboyBridge.closeMessage();
            });
        });
        // *****************************************************
    </script>
</body>
<a href="appboy://close">X</a>
</html>
```
2. Click primary or secondary button and check deep link activity is opened
3. Click system back button and verify html overlay remains forever

## How to avoid this situation?

### Option 1

Explicitly inject button functions to `DOM` objects and use `onclick` instead of `addEventListener`

Before

``` html
document.querySelectorAll("[data-button-id]").forEach(function (node) {
  node.addEventListener("click", function () {
    appboyBridge.logClick(node.dataset.buttonId);
    appboyBridge.closeMessage();
  });
});
```

After

``` html
document.querySelector(`[data-button-id="close"]`).onclick = function() {
  appboyBridge.logClick(node.dataset.buttonId);
  appboyBridge.closeMessage();
}
document.querySelector(`[data-button-id="1"]`).onclick = function() {
  appboyBridge.logClick(node.dataset.buttonId);
  appboyBridge.closeMessage();
}
document.querySelector(`[data-button-id="0"]`).onclick = function() {
  appboyBridge.logClick(node.dataset.buttonId);
  appboyBridge.closeMessage();
}
```

### Option 2

Pull out `query selector` and just use `onclick` attribute

``` html
<body>
  <div class="overlay">
    <div class="modal">
      <button onclick="click()" data-button-id="close" class="modal__cross modal__cross-mobile">
        <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path fill-rule="evenodd" clip-rule="evenodd"
            d="M14.4175 16.8419L8.51467 10.9391C7.8452 10.2696 7.8452 9.18422 8.51467 8.51475C9.18414 7.84528 10.2696 7.84528 10.939 8.51475L16.8418 14.4176L22.7446 8.51475C23.4141 7.84528 24.4995 7.84528 25.169 8.51475C25.8385 9.18422 25.8385 10.2696 25.169 10.9391L19.2662 16.8419L25.169 22.7447C25.8385 23.4142 25.8385 24.4996 25.169 25.1691C24.4995 25.8386 23.4141 25.8386 22.7446 25.1691L16.8418 19.2663L10.939 25.1691C10.2696 25.8386 9.18414 25.8386 8.51467 25.1691C7.8452 24.4996 7.8452 23.4142 8.51467 22.7447L14.4175 16.8419Z"
            fill="#A8B3B8" />
        </svg>
      </button>
      <div class="modal__image image-1"></div>
      <div class="modal__content">
        <button data-button-id="close" class="modal__cross modal__cross-web">
          <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M14.4175 16.8419L8.51467 10.9391C7.8452 10.2696 7.8452 9.18422 8.51467 8.51475C9.18414 7.84528 10.2696 7.84528 10.939 8.51475L16.8418 14.4176L22.7446 8.51475C23.4141 7.84528 24.4995 7.84528 25.169 8.51475C25.8385 9.18422 25.8385 10.2696 25.169 10.9391L19.2662 16.8419L25.169 22.7447C25.8385 23.4142 25.8385 24.4996 25.169 25.1691C24.4995 25.8386 23.4141 25.8386 22.7446 25.1691L16.8418 19.2663L10.939 25.1691C10.2696 25.8386 9.18414 25.8386 8.51467 25.1691C7.8452 24.4996 7.8452 23.4142 8.51467 22.7447L14.4175 16.8419Z"
              fill="#A8B3B8" />
          </svg>
        </button>
        <h1 class="modal__title">This is where the modal title goes</h1>
        <h2 class="modal__sub-title">Sub head maybe here that is not too long</h2>
        <p class="modal__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
        <div class="modal__button-wrap">
          <a onclick="click()" href="iam://secondary_clicked" class="modal__btn modal__btn--secondary" data-button-id="1">Secondary
            Action</a>
          <a onclick="click()" href="iam://primary_clicked" class="modal__btn modal__btn--main" data-button-id="0">Primary
            Action</a>
        </div>
      </div>
    </div>
  </div>

  <script>
    function click() {
      appboyBridge.closeMessage();
    }
  </script>
</body>
```

## Conclusion

The template that Braze provided codes already contain problematic parts. Our customers possible to avoid though the modified html codes as I attached before but in my opition, those modifications are temporary solution because most of our customers are `marketer` that means they are not familiar with html and so much easily possible to make mistake without developers confirmation.

I hope fix the overlay issue wheter whatever html code is attached into in app message.
