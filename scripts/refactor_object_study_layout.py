#!/usr/bin/env python3
"""
『오브젝트』 스터디 저장소 디렉터리 구조로 theater/ 코드를 재배치하고
패키지·import 선언을 경로에 맞게 갱신합니다.
"""
from __future__ import annotations

import re
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent


def path_to_package(relpath: Path) -> str:
    parts = list(relpath.parts[:-1])
    return ".".join(parts)


def rewrite_package_line(content: str, new_package: str) -> str:
    return re.sub(
        r"^package\s+[\w.]+;",
        f"package {new_package};",
        content,
        count=1,
        flags=re.MULTILINE,
    )


def rewrite_import_line(line: str, old_prefix: str, new_prefix: str) -> str:
    m = re.match(r"^(\s*import\s+(?:static\s+)?)([\w.]+)(\s*;.*)$", line)
    if not m:
        return line
    pre, body, suf = m.group(1), m.group(2), m.group(3)
    op = old_prefix if old_prefix.endswith(".") else old_prefix + "."
    np = new_prefix if new_prefix.endswith(".") else new_prefix + "."
    if body.startswith(op):
        return pre + np + body[len(op) :] + suf
    return line


# (경로 부분 문자열, [(old, new), ...]) — 긴 needle 이 먼저 매칭되도록 정렬해서 사용
IMPORT_CONTEXT_RULES: list[tuple[str, list[tuple[str, str]]]] = [
    ("ch01_basic_oop/after/theater/v2/", [("v2.", "ch01_basic_oop.after.theater.v2.")]),
    ("ch02_discount_policy/after/theater/v2/", [("v2.", "ch02_discount_policy.after.theater.v2.")]),
    ("ch01_basic_oop/before/theater/v0/", [("v0.", "ch01_basic_oop.before.theater.v0.")]),
    ("ch02_discount_policy/before/theater/v0/", [("v0.", "ch02_discount_policy.before.theater.v0.")]),
    ("ch01_basic_oop/after/theater/v1/", [("v1.", "ch01_basic_oop.after.theater.v1.")]),
    ("ch02_discount_policy/before/theater/v1/", [("v1.", "ch02_discount_policy.before.theater.v1.")]),
    ("ch01_basic_oop/after/theater/new_theater_v0/", [("new_theater_v0.", "ch01_basic_oop.after.theater.new_theater_v0.")]),
    ("ch01_basic_oop/after/theater/new_theater_v1/", [("new_theater_v1.", "ch01_basic_oop.after.theater.new_theater_v1.")]),
    ("ch01_basic_oop/after/theater/new_theater_v2/", [("new_theater_v2.", "ch01_basic_oop.after.theater.new_theater_v2.")]),
]

IMPORT_CONTEXT_RULES.sort(key=lambda x: -len(x[0]))

GLOBAL_IMPORT_REPLACEMENTS: list[tuple[str, str]] = [
    ("v0.", "ch01_basic_oop.before.theater.v0."),
    ("v1.", "ch01_basic_oop.after.theater.v1."),
    ("v2.", "ch02_discount_policy.after.theater.v2."),
    ("new_theater_v0.", "ch01_basic_oop.after.theater.new_theater_v0."),
    ("new_theater_v1.", "ch01_basic_oop.after.theater.new_theater_v1."),
    ("new_theater_v2.", "ch01_basic_oop.after.theater.new_theater_v2."),
    ("phone.v1.", "ch03_phone_billing.v1."),
    ("phone.v2.", "ch03_phone_billing.v2."),
    ("phone.v3.", "ch03_phone_billing.v3."),
    ("phone.v4.", "ch03_phone_billing.v4."),
    ("test.claude6_1.", "ch05_message_interface.after.claude6_1."),
    ("test.claude6_2.", "ch05_message_interface.after.claude6_2."),
    ("test.claude6_3.", "ch05_message_interface.after.claude6_3."),
    ("apec.order.", "ch04_solid_principles.after.apec.order."),
    ("apec.formatter.", "ch04_solid_principles.after.apec.formatter."),
    ("apec.notification.", "ch04_solid_principles.after.apec.notification."),
    ("apec.order_factory.", "ch04_solid_principles.after.apec.order_factory."),
    ("apec.stock_delivery.", "ch04_solid_principles.after.apec.stock_delivery."),
    ("apec.discount.", "ch04_solid_principles.after.apec.discount."),
    ("apec.sql.", "ch06_dependency.after.apec.sql."),
    ("apec.spring_payment.", "ch06_dependency.after.apec.spring_payment."),
    ("apec.report.", "ch06_dependency.after.apec.report."),
    ("apec.payment_order_point.", "ch08_shopping_system.after.apec.payment_order_point."),
    ("apec.final_prjct.", "ch07_design_patterns.after.apec.final_prjct."),
    ("notification.", "ch07_design_patterns.after.notification."),
    ("delivery.", "ch07_design_patterns.after.delivery."),
]


def apply_imports_for_file(rel: str, line: str) -> str:
    if not line.lstrip().startswith("import "):
        return line
    for needle, pairs in IMPORT_CONTEXT_RULES:
        if needle in rel:
            for old_p, new_p in pairs:
                line = rewrite_import_line(line, old_p, new_p)
            return line
    for old_p, new_p in GLOBAL_IMPORT_REPLACEMENTS:
        line = rewrite_import_line(line, old_p, new_p)
    return line


def process_java_file(java_path: Path) -> None:
    rel = java_path.relative_to(ROOT).as_posix()
    pkg = path_to_package(java_path.relative_to(ROOT))
    text = java_path.read_text(encoding="utf-8")
    text = rewrite_package_line(text, pkg)
    lines = text.splitlines(keepends=True)
    out: list[str] = []
    for line in lines:
        out.append(apply_imports_for_file(rel, line))
    java_path.write_text("".join(out), encoding="utf-8")


def mkdir(p: Path) -> None:
    p.mkdir(parents=True, exist_ok=True)


def move(src: Path, dst: Path) -> None:
    mkdir(dst.parent)
    if not src.exists():
        raise FileNotFoundError(src)
    shutil.move(str(src), str(dst))


def copytree(src: Path, dst: Path) -> None:
    if dst.exists():
        shutil.rmtree(dst)
    shutil.copytree(src, dst)


def main() -> None:
    t = ROOT / "theater"
    apec = t / "apec"

    move(t / "common", ROOT / "common")

    move(t / "v0", ROOT / "ch01_basic_oop/before/theater/v0")
    move(t / "v1", ROOT / "ch01_basic_oop/after/theater/v1")
    move(t / "new_theater_v0", ROOT / "ch01_basic_oop/after/theater/new_theater_v0")
    move(t / "new_theater_v1", ROOT / "ch01_basic_oop/after/theater/new_theater_v1")
    move(t / "new_theater_v2", ROOT / "ch01_basic_oop/after/theater/new_theater_v2")

    move(t / "v2", ROOT / "ch02_discount_policy/after/theater/v2")
    copytree(
        ROOT / "ch02_discount_policy/after/theater/v2",
        ROOT / "ch01_basic_oop/after/theater/v2",
    )

    copytree(
        ROOT / "ch01_basic_oop/before/theater/v0",
        ROOT / "ch02_discount_policy/before/theater/v0",
    )
    copytree(
        ROOT / "ch01_basic_oop/after/theater/v1",
        ROOT / "ch02_discount_policy/before/theater/v1",
    )

    for ver in ("v1", "v2", "v3", "v4"):
        move(t / "phone" / ver, ROOT / f"ch03_phone_billing/{ver}")
    shutil.rmtree(t / "phone")

    move(t / "test" / "claude6_1", ROOT / "ch05_message_interface/after/claude6_1")
    move(t / "test" / "claude6_2", ROOT / "ch05_message_interface/after/claude6_2")
    move(t / "test" / "claude6_3", ROOT / "ch05_message_interface/after/claude6_3")
    shutil.rmtree(t / "test")

    mkdir(ROOT / "ch05_message_interface/before/claude6_1/docs")
    mkdir(ROOT / "ch05_message_interface/before/claude6_2/docs")
    mkdir(ROOT / "ch05_message_interface/before/claude6_3/docs")
    shutil.copy2(
        ROOT / "ch05_message_interface/after/claude6_1/docs/문제.md",
        ROOT / "ch05_message_interface/before/claude6_1/docs/문제.md",
    )
    shutil.copy2(
        ROOT / "ch05_message_interface/after/claude6_2/docs/문제.md",
        ROOT / "ch05_message_interface/before/claude6_2/docs/문제.md",
    )
    shutil.copy2(
        ROOT / "ch05_message_interface/after/claude6_3/docs/문제.md",
        ROOT / "ch05_message_interface/before/claude6_3/docs/문제.md",
    )

    for name in (
        "order",
        "formatter",
        "notification",
        "order_factory",
        "stock_delivery",
        "discount",
    ):
        move(apec / name, ROOT / f"ch04_solid_principles/after/apec/{name}")

    mkdir(ROOT / "ch04_solid_principles/before/docs/order")
    mkdir(ROOT / "ch04_solid_principles/before/docs/formatter")
    od = ROOT / "ch04_solid_principles/after/apec/order/docs/docs.md"
    if od.exists():
        shutil.copy2(od, ROOT / "ch04_solid_principles/before/docs/order/docs.md")
    fd = ROOT / "ch04_solid_principles/after/apec/formatter/docs/docs.md"
    if fd.exists():
        shutil.copy2(fd, ROOT / "ch04_solid_principles/before/docs/formatter/docs.md")

    for name in ("sql", "spring_payment", "report"):
        move(apec / name, ROOT / f"ch06_dependency/after/apec/{name}")

    mkdir(ROOT / "ch06_dependency/before/docs")
    sql_doc = ROOT / "ch06_dependency/after/apec/sql/docs/docs.md"
    if sql_doc.exists():
        shutil.copy2(sql_doc, ROOT / "ch06_dependency/before/docs/sql_docs.md")
    sp_doc = ROOT / "ch06_dependency/after/apec/spring_payment/docs.md"
    if sp_doc.exists():
        shutil.copy2(sp_doc, ROOT / "ch06_dependency/before/docs/spring_payment_docs.md")
    if (apec / "test9/docs.md").exists():
        shutil.copy2(
            apec / "test9/docs.md",
            ROOT / "ch06_dependency/before/docs/test9_docs.md",
        )

    move(t / "delivery", ROOT / "ch07_design_patterns/after/delivery")
    move(t / "notification", ROOT / "ch07_design_patterns/after/notification")
    move(apec / "final_prjct", ROOT / "ch07_design_patterns/after/apec/final_prjct")

    mkdir(ROOT / "ch07_design_patterns/before/docs")
    nr = ROOT / "ch07_design_patterns/after/notification/docs/REFACTORING.md"
    if nr.exists():
        shutil.copy2(nr, ROOT / "ch07_design_patterns/before/docs/notification_REFACTORING.md")

    move(apec / "payment_order_point", ROOT / "ch08_shopping_system/after/apec/payment_order_point")

    shutil.rmtree(apec)

    if (t / "theory").exists():
        mkdir(ROOT / "docs")
        move(t / "theory", ROOT / "docs/theory")

    shutil.rmtree(t)

    for java in ROOT.rglob("*.java"):
        if ".git" in java.parts or "scripts" in java.parts:
            continue
        process_java_file(java)

    print("Refactor complete.")


if __name__ == "__main__":
    main()
