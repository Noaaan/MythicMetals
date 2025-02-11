import json
from pathlib import Path
import re

ROOT_DIR = Path(__file__).resolve().parent.parent.parent
JSON_DIR = ROOT_DIR / "src/main/resources/assets/mythicmetals/lang"
LANG_SOURCE = JSON_DIR / "en_us.json"  # Original language file, set to English by default
LANG_TARGET = JSON_DIR / "zh_cn.json"  # Target language file, set to your own language file
IGNORE_PATTERNS = [r"doge", r"froge", r".color"]


def load_json(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        return json.load(f)


def should_ignore_key(key, ignore_patterns):
    """
    Judge whether the key should be ignored (supports regex matching).

    Parameters
    ----------
    key : str
        The key to be checked (full path, e.g., "metadata.author").
    ignore_patterns : list
        List of regex patterns to ignore.

    Returns
    -------
    bool
        Whether to ignore (True / False).
    """
    return any(re.search(pattern, key) for pattern in ignore_patterns)


def find_untranslated_keys(source, localized, untranslated_keys=None, parent_key="", ignore_patterns=None):
    """
    Look for untranslated keys recursively, supporting the use of
    regular expressions to exclude specific keys.

    Parameters
    ----------
    source
        Original JSON data.
    localized
        Localized JSON data.
    untranslated_keys : list, optional
        Record untranslated keys.
    parent_key : str, optional
        Record nested key paths.
    ignore_patterns : list, optional
        List of regular expression patterns to exclude keys.

    Returns
    -------
    untranslated_keys : list
        Untranslated keys.
    """
    if untranslated_keys is None:
        untranslated_keys = []
    if ignore_patterns is None:
        ignore_patterns = []

    if isinstance(source, dict):
        for key, value in source.items():
            new_key = f"{parent_key}.{key}" if parent_key else key

            if should_ignore_key(new_key, ignore_patterns):
                continue

            if key in localized:
                if isinstance(value, (dict, list)):
                    find_untranslated_keys(
                        value, localized[key], untranslated_keys, new_key, ignore_patterns
                    )
                elif localized[key] == value:
                    untranslated_keys.append(new_key)
            else:
                untranslated_keys.append(new_key)

    elif isinstance(source, list) and isinstance(localized, list):
        for index, (s_item, l_item) in enumerate(zip(source, localized)):
            find_untranslated_keys(
                s_item, l_item, untranslated_keys, f"{parent_key}[{index}]", ignore_patterns
            )

    return untranslated_keys


source_json = load_json(LANG_SOURCE)
localized_json = load_json(LANG_TARGET)

untranslated_keys = find_untranslated_keys(source_json, localized_json, ignore_patterns=IGNORE_PATTERNS)

if untranslated_keys:
    print(f"You have {len(untranslated_keys)} untranslated keys:")
    for key in untranslated_keys:
        print(key)
else:
    print("All keys are translated!")
