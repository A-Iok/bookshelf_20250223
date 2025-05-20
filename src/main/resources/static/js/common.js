//postリクエストを行う
async function sendPostRequest(path, requestJson) {
    try {
        const response = await fetch(path, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: requestJson
        });

        return response; // Responseオブジェクトをそのまま返却
    } catch (error) {
        console.error("リクエスト送信中にエラーが発生しました:", error);
        return null; // エラー時はnullを返す
    }
}

//getリクエストを行う
async function sendGetRequest(path) {
    console.log("sendGetRequest,path=" + path);
    try {
        const response = await fetch(path, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        });

        return response; // Responseオブジェクトをそのまま返却
    } catch (error) {
        console.error("リクエスト送信中にエラーが発生しました:", error);
        return null; // エラー時はnullを返す
    }
}

async function handleResponse(response) {
    if (!response) {
        console.error("レスポンスがありません");
        return;
    }

    switch (response.status) {
        case 200:
            console.log("成功: データが正常に取得されました");
            // console.log(await response.text()); // レスポンスのJSONを出力
            break;
        case 201:
            console.log("作成成功: 新しいリソースが作成されました");
            // console.log(await response.text());
            break;
        case 400:
            console.error("エラー: リクエストが不正です");
            break;
        case 404:
            console.error("エラー: リソースが見つかりません");
            break;
        case 500:
            console.error("サーバーエラー: 内部サーバーエラーが発生しました");
            break;
        default:
            console.warn(`警告: 予期しないステータスコード (${response.status})`);
            break;
    }
}

//Jsonの整形
var parseJson = function (data) {
    var returnJson = {};
    for (idx = 0; idx < data.length; idx++) {
        returnJson[data[idx].name] = data[idx].value
    }
    return JSON.stringify(returnJson);
}

// 4xx系, 5xx系のエラーをさばくハンドラ
var handleErrors = function (response) {
    // 4xx系, 5xx系エラーのときには response.ok = false になる
    if (!response.ok) {
        throw Error(response.statusText);
    }

    return response;
}

// レスポンスに対して共通で行う前処理
var prepare = function (response) {
    // ステータスコードとステータステキストを表示
    console.info('ok?: ', response.ok);
    console.info('status: ', response.status);
    console.info('statusText: ', response.statusText);

    // レスポンスボディをJSONとしてパース
    return response.json();
}

// 正常終了時の処理
var onFulfilled = function (data) {
    var message = ([
        '成功',
        'data: ' + JSON.stringify(data, null, '  '),
    ]).join('\n');

    console.log(data);
    alert(message);
};

// エラー終了時の処理
var onRejected = function (err) {
    var message = ([
        '失敗',
        'error: ' + err.message,
    ]).join('\n');

    console.error(err);
    alert(message);
};

//画面遷移（共通）

//ID指定で画面遷移を行う
function goToPageById(filename, id) {
    goToPage(filename, "?id=" + encodeURIComponent(id));
}

//画面遷移を行う
function goToPage(filename, params) {
    window.location.href = filename + params; // 指定した HTML ファイルへ遷移
}

//API呼び出し
//ユーザー情報取得
async function getUser(id) {
    console.log("getuser");
    const response = await sendGetRequest("/user" + "?id=" + id);
    await handleResponse(response);
    const data = await response.json();
    document.getElementById("id").textContent = data.id;
    document.getElementById("name").textContent = data.name;
}

//本棚取得
async function getBooks(userId, bookshelfId) {
    console.log("getbooks");
    const response = await sendGetRequest("/user/books" + "?userId=" + userId + "&bookshelfId=" + bookshelfId);
    await handleResponse(response);
    const data = await response.json();
    document.getElementById("id").textContent = data.id;
    document.getElementById("name").textContent = data.name;
}