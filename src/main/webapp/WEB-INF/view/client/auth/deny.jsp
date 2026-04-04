<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>403 - Khong co quyen truy cap</title>

    <style>
      * {
        box-sizing: border-box;
      }

      body {
        margin: 0;
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 24px;
        font-family: Arial, Helvetica, sans-serif;
        color: #1f2937;
        background:
          radial-gradient(circle at top, #fef3c7 0, #fff7ed 30%, #f8fafc 100%);
      }

      .page {
        width: 100%;
        max-width: 680px;
      }

      .card {
        background: #ffffff;
        border-radius: 24px;
        padding: 40px 32px;
        text-align: center;
        box-shadow: 0 20px 45px rgba(15, 23, 42, 0.12);
      }

      .code {
        margin: 0;
        font-size: clamp(64px, 12vw, 112px);
        line-height: 1;
        font-weight: 800;
        color: #dc2626;
      }

      .title {
        margin: 16px 0 12px;
        font-size: 28px;
        font-weight: 700;
      }

      .description {
        margin: 0 auto;
        max-width: 460px;
        font-size: 16px;
        line-height: 1.7;
        color: #4b5563;
      }

      .actions {
        margin-top: 28px;
        display: flex;
        flex-wrap: wrap;
        gap: 12px;
        justify-content: center;
      }

      .button,
      .button-secondary {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        min-width: 170px;
        padding: 12px 20px;
        border-radius: 999px;
        border: 1px solid transparent;
        font-size: 15px;
        font-weight: 600;
        text-decoration: none;
        cursor: pointer;
        transition: transform 0.2s ease, box-shadow 0.2s ease,
          background-color 0.2s ease;
      }

      .button {
        color: #ffffff;
        background: #2563eb;
        box-shadow: 0 10px 20px rgba(37, 99, 235, 0.22);
      }

      .button-secondary {
        color: #1f2937;
        background: #f3f4f6;
        border-color: #e5e7eb;
      }

      .button:hover,
      .button-secondary:hover {
        transform: translateY(-1px);
      }

      .button-secondary-form {
        margin: 0;
      }

      .helper {
        margin-top: 18px;
        font-size: 14px;
        color: #6b7280;
      }

      @media (max-width: 576px) {
        .card {
          padding: 32px 20px;
        }

        .actions {
          flex-direction: column;
        }

        .button,
        .button-secondary {
          width: 100%;
        }
      }
    </style>
  </head>
  <body>
    <main class="page">
      <section class="card">
        <p class="code">403</p>
        <h1 class="title">Ban khong co quyen truy cap trang nay</h1>
        <p class="description">
          Tai khoan hien tai khong du quyen de mo noi dung ban vua yeu cau. Ban
          co the quay lai trang chu hoac dang nhap bang tai khoan khac.
        </p>

        <div class="actions">
          <a class="button" href="/">Ve trang chu</a>
          <button class="button-secondary" type="button" onclick="history.back()">
            Quay lai
          </button>

        </div>

        <p class="helper">Neu ban nghi day la loi, hay kiem tra lai quyen tai khoan.</p>
      </section>
    </main>
  </body>
</html>
