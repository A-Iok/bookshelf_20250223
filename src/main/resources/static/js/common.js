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
    console.log("goToPageById");
    goToPage(filename, "?id=" + encodeURIComponent(id));
}

//画面遷移を行う
function goToPage(filename, params) {
    window.location.href = filename + params; // 指定した HTML ファイルへ遷移
}

//API呼び出し
/**
 * ユーザー情報取得
 * @param {string} id 
 */
async function getUser(id) {
    console.log("getuser");
    const response = await sendGetRequest("/user" + "?id=" + id);
    await handleResponse(response);
    const data = await response.json();
    document.getElementById("id").textContent = data.id;
    document.getElementById("name").textContent = data.name;
}

/**
 * 本棚取得（トップ画面）
 * @param {string} userId 
 * @param {string} bookshelfId 
 */
async function getBooks(userId, bookshelfId) {
    console.log("getbooks");

    const response = await sendGetRequest("/user/books" + "?userId=" + userId + "&bookshelfId=" + bookshelfId);
    await handleResponse(response);
    console.log(response);
    console.log(response.json);
    const data = await response.json();
    console.log(data);

    //画面表示作成
    //配列を取り出す
    const books = data.books;

    const bookshelfTableId = getBookshelfTableId(bookshelfId);

    // テーブルのtbodyを取得
    const tableBody = document.querySelector("#" + bookshelfTableId + " tbody");

    // 一度tbodyをクリア（既存行を削除）
    tableBody.innerHTML = "";

    // データを1件ずつテーブルに追加
    books.forEach(book => {
        const row = document.createElement("tr");
        row.className = "table-light";

        const nameCell = document.createElement("th");
        nameCell.scope = "row";
        nameCell.textContent = book.name;

        const authorCell = document.createElement("td");
        authorCell.textContent = book.authorName;

        row.appendChild(nameCell);
        row.appendChild(authorCell);
        tableBody.appendChild(row);
    });
}

const bookshelf = [
    {
        bookshelfId: 1,
        name: 'ほしい本棚',
        tableId: "wish-books"
    },
    {
        bookshelfId: 2,
        name: '今の本棚',
        tableId: "collect-books"
    },
    {
        bookshelfId: 3,
        name: '読んでいる本棚',
        tableId: "read-books"
    }
];

/**
 * 本棚IDから本棚テーブルID取得
 * @param {string} bookshelfId 
 * @returns 
 */
function getBookshelfTableId(bookshelfId) {

    const result = bookshelf.find((e) => e.bookshelfId == bookshelfId);

    console.log("getBookshelfTableId：" + result.tableId);
    return result.tableId;
}

/**
 * 本棚IDから本棚名取得
 * @param {string} bookshelfId 
 * @returns 
 */
function getBookshelfName(bookshelfId) {

    const result = bookshelf.find((e) => e.bookshelfId == bookshelfId);

    console.log("getBookshelfName" + result.name);
    return result.name;
}

/**
 * 本棚テーブルIDから本棚ID取得
 * @param {string} bookshelfId 
 * @returns 
 */
function getBookshelfId(bookshelfTableId) {

    const result = bookshelf.find((e) => e.tableId == bookshelfTableId);

    console.log("getBookshelfId：" + result.bookshelfId);
    return result.bookshelfId;
}

//Todo 本取得処理を共通化する
/**
 * 本棚取得（詳細画面）
 * @param {string} userId 
 * @param {string} bookshelfId 
 */
async function getBooksDetail(userId, bookshelfId) {
    console.log("getbooksdetail");

    const response = await sendGetRequest("/user/books" + "?userId=" + userId + "&bookshelfId=" + bookshelfId);
    await handleResponse(response);
    console.log(response);
    console.log(response.json);
    const data = await response.json();
    console.log(data);

    //画面表示作成
    //配列を取り出す
    const books = data.books;

    const bookshelfTableId = getBookshelfTableId(bookshelfId);

    // テーブルのtbodyを取得
    const tableBody = document.querySelector("#" + bookshelfTableId + " tbody");

    // 一度tbodyをクリア（既存行を削除）
    tableBody.innerHTML = "";

    // データを1件ずつテーブルに追加
    books.forEach(book => {
        const row = document.createElement("tr");
        row.className = "table-light";

        const nameCell = document.createElement("th");
        nameCell.scope = "row";
        nameCell.textContent = book.name;

        const authorCell = document.createElement("td");
        authorCell.textContent = book.authorName;

        const publisherCell = document.createElement("td");
        publisherCell.textContent = book.publisherName;

        const createdAtCell = document.createElement("td");
        createdAtCell.textContent = book.createdAt;

        row.appendChild(nameCell);
        row.appendChild(authorCell);
        row.appendChild(publisherCell);
        row.appendChild(createdAtCell);
        tableBody.appendChild(row);
    });
}